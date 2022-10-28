package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

import java.lang.reflect.Method;
import java.util.ArrayList;


public class Nave extends ObjetoColisionable {
	private boolean destruida = false;
    private int vidas = 3;
    private float xVel = 0;
    private float yVel = 0;
    final private Sound sonidoHerido;
    private boolean herido = false;
    final private int tiempoHeridoMax=50;
    private int tiempoHerido;
    private int rotacion = 0;
    private Armamento arma;
    
    public Nave(int x, int y, Texture tx, Sound soundChoque, Texture texture, Sound sound) {
    	sonidoHerido = soundChoque;
    	spr = new Sprite(tx);
    	spr.setPosition(x, y);
    	//spr.setOriginCenter();
    	spr.setBounds(x, y, 45, 45);
        arma = new Armamento(1,40,2);
    }

    public void update() {
        float x = spr.getX();
        float y = spr.getY();
        if (!herido) {
            if (Gdx.input.isKeyPressed(Input.Keys.A)) spr.setRotation(++rotacion);
            if (Gdx.input.isKeyPressed(Input.Keys.D)) spr.setRotation(--rotacion);

            if (Gdx.input.isKeyPressed(Input.Keys.W)) {
                xVel -= Math.sin(Math.toRadians(rotacion)) * 0.1;
                if (xVel > 1) {
                    xVel = 1;
                }
                yVel += Math.cos(Math.toRadians(rotacion)) * 0.1;
                if (yVel > 2.25f) {
                    yVel = 2.25f;
                }
            }
            if (Gdx.input.isKeyPressed(Input.Keys.S)) {
                xVel += Math.sin(Math.toRadians(rotacion)) * 0.1;
                if (xVel > 1) {
                    xVel = 1;
                }
                yVel -= Math.cos(Math.toRadians(rotacion)) * 0.1;
                if (yVel < -2.25f) {
                    yVel = -2.25f;
                }

            }

            // que se mantenga dentro de los bordes de la ventana
            if (x + xVel < 0 || x + xVel + spr.getWidth() > Gdx.graphics.getWidth())
                xVel *= -1;
            if (y + yVel < 0 || y + yVel + spr.getHeight() > Gdx.graphics.getHeight())
                yVel *= -1;

            spr.setPosition(x + xVel, y + yVel);
        } else {
            spr.setX(spr.getX() + MathUtils.random(-2, 2));
            spr.setX(x);
            tiempoHerido--;
            if (tiempoHerido <= 0) herido = false;
        }
        // disparo
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            //Proyectil bala = new Proyectil(spr.getX() + spr.getWidth() / 2 - 5, spr.getY() + spr.getHeight() - 5, 3, 3, txBala, rotacion);
            arma.disparar(spr.getX() + spr.getWidth() / 2 - 5, spr.getY() + spr.getHeight() - 5,rotacion);
            //soundBala.play(0.3f);              esto esta en arma.disparar pero por si no funciona xd
        }
    }
    public void colisionado(Asteroide b) {
        if (xVel ==0) xVel += b.getXSpeed()/2;
        xVel = - xVel;

        if (yVel ==0) yVel += b.getySpeed()/2;
        yVel = - yVel;
        //actualizar vidas y herir
        this.vidas--;
        herido = true;
        tiempoHerido=tiempoHeridoMax;
        sonidoHerido.play();
        if (vidas<=0)
            destruida = true;
    }
    public void colisionado(Nave nave) {
        // imposible
    }
    public void colisionado(Proyectil proyectil) {
        // no importa
    }
    
    public boolean isDestroyed() {
       return !herido && destruida;
    }
    public boolean estaHerido() {
 	   return herido;
    }
    public Rectangle getArea() {
        return new Rectangle(spr.getX(), spr.getY(), spr.getWidth(), spr.getHeight());
    }
    public int getVidas() {return vidas;}
	public void setVidas(int vidas2) {vidas = vidas2;}
    public float getVelX() {return xVel;}
    public float getVelY() {return yVel;}
}
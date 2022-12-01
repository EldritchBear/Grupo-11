package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.proyectil.Proyectil;

public class Nave extends ObjetoFisico {
	private boolean destruida = false;
    private int vidas = 3;
    private float xVel = 0;
    private float yVel = 0;
    final private Sound sonidoHerido;
    private boolean herido = false;
    final private int tiempoHeridoMax=50;
    private int tiempoHerido;
    private int rotacion = 0;
    final private Armamento arma;
    
    public Nave(int x, int y, Texture tx, Sound soundChoque) {
        super(new Sprite(tx));
        sonidoHerido = soundChoque;
    	spr.setPosition(x, y);
    	spr.setBounds(x, y, 45, 45);
        arma = new Armamento(1,40,4);
    }

    public void update() {
        arma.disminuirCdBala();
        float x = spr.getX();
        float y = spr.getY();
        if (!herido) {
            if (Gdx.input.isKeyPressed(Input.Keys.A)) spr.setRotation(++rotacion);
            if (Gdx.input.isKeyPressed(Input.Keys.D)) spr.setRotation(--rotacion);

            if (Gdx.input.isKeyPressed(Input.Keys.W)) {
                xVel -= Math.sin(Math.toRadians(rotacion)) * 0.1;
                if (xVel > 3) {
                    xVel = 3;
                }
                yVel += Math.cos(Math.toRadians(rotacion)) * 0.1;
                if (yVel > 3.25f) {
                    yVel = 3.25f;
                }
            }
            if (Gdx.input.isKeyPressed(Input.Keys.S)) {
                xVel += Math.sin(Math.toRadians(rotacion)) * 0.1;
                if (xVel > 3) {
                    xVel = 3;
                }
                yVel -= Math.cos(Math.toRadians(rotacion)) * 0.1;
                if (yVel < -3.25f) {
                    yVel = -3.25f;
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
            arma.disparar(spr.getX() + spr.getWidth() / 2 - 5, spr.getY() + spr.getHeight() - 5,rotacion);
        }
    }
    public void colisionado(ObjetoFisico objeto) {
        objeto.colisionado(this);
    }
    public void colisionado(Asteroide b) {
        if (this.herido) return;
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
    public void colisionado(AsteroideJefe asteroide) {
        colisionado((Asteroide)asteroide);
    }
    public void colisionado(Nave nave) {
//        throw new ErrorDeFisicaException("La nave colisionó con otra nave, pero solo debería existir una.");
    }
    public void colisionado(Proyectil proyectil) {}
    
    public boolean isDestroyed() {
       return !herido && destruida;
    }
    public Rectangle getArea() {
        return new Rectangle(spr.getX(), spr.getY(), spr.getWidth(), spr.getHeight());
    }
    public int getVidas() {return vidas;}
	public void setVidas(int vidas2) {vidas = vidas2;}
    public float getVelX() {return xVel;}
    public float getVelY() {return yVel;}
}
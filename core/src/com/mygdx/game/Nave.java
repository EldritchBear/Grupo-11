package com.mygdx.game;

import com.mygdx.game.asteroides.Asteroide;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.proyectil.Proyectil;

public class Nave extends Elemento {
	private boolean destruida = false;
    private final Vidas vidas;
    private float xVel = 0;
    private float yVel = 0;
    final private Sound sonidoHerido;
    private boolean herido = false;
    final private int tiempoHeridoMax=50;
    private int tiempoHerido;
    private int rotacion = 0;
    final private Armamento arma;
    
    public Nave(int x, int y, Texture tx, Sound soundChoque, Vidas vidas) {
        super(new Sprite(tx));
        this.vidas = vidas;
        sonidoHerido = soundChoque;
    	setPosition(x, y);
    	setBounds(x, y, 45, 45);
        arma = new Armamento(1,40,4, 25);
    }

    public void update() {
        arma.disminuirCdBala();
        float x = getX();
        float y = getY();
        if (!herido) {
            if (Gdx.input.isKeyPressed(Input.Keys.A)) setRotation(++rotacion);
            if (Gdx.input.isKeyPressed(Input.Keys.D)) setRotation(--rotacion);

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
            if (x + xVel < 0 || x + xVel + getWidth() > Gdx.graphics.getWidth())
                xVel *= -1;
            if (y + yVel < 0 || y + yVel + getHeight() > Gdx.graphics.getHeight())
                yVel *= -1;

            setPosition(x + xVel, y + yVel);
        } else {
            setX(getX() + MathUtils.random(-2, 2));
            setX(x);
            tiempoHerido--;
            if (tiempoHerido <= 0) herido = false;
        }
        // disparo
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            arma.disparar(getX() + getWidth() / 2 - 5, getY() + getHeight() - 5,rotacion);
        }
    }
    public void colisionado(Elemento objeto) {
        objeto.colisionado(this);
    }
    public void colisionado(Asteroide b) {
        if (this.herido) return;
        if (xVel ==0) xVel += b.getXSpeed()/2;
        xVel = - xVel;

        if (yVel ==0) yVel += b.getySpeed()/2;
        yVel = - yVel;
        //actualizar vidas y herir
        vidas.reducirVidas(1);
        herido = true;
        tiempoHerido=tiempoHeridoMax;
        sonidoHerido.play();
        if (vidas.getVidas()<=0)
            destruida = true;
    }
    public void colisionado(Nave nave) {}
    public void colisionado(Proyectil proyectil) {}
    
    public boolean isDestroyed() {
       return !herido && destruida;
    }
    public Rectangle getArea() {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }
    public int getVidas() {return vidas.getVidas();}
    public float getVelX() {return xVel;}
    public float getVelY() {return yVel;}
}
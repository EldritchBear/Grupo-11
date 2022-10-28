package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;
import java.lang.reflect.*;


public class Asteroide implements Objeto {
    private int x;
    private int y;
    private int xSpeed;
    private int ySpeed;
    final private Sprite spr;
    private int hp;

    public Asteroide(int x, int y, int size, int xSpeed, int ySpeed, Texture texture, int hp) {
        spr = new Sprite(texture);

        this.x = x;
        //validar que borde de esfera no quede fuera
        if (x-size < 0) this.x = x+size;
        if (x+size > Gdx.graphics.getWidth())this.x = x-size;

        this.y = y;
        //validar que borde de esfera no quede fuera
        if (y-size < 0) this.y = y+size;
        if (y+size > Gdx.graphics.getHeight())this.y = y-size;

        spr.setPosition(x, y);
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.hp = hp;
    }
    public void update() {
        x += getXSpeed();
        y += getySpeed();

        if (x+getXSpeed() < 0 || x+getXSpeed()+spr.getWidth() > Gdx.graphics.getWidth())
        	setXSpeed(getXSpeed() * -1);
        if (y+getySpeed() < 0 || y+getySpeed()+spr.getHeight() > Gdx.graphics.getHeight())
        	setySpeed(getySpeed() * -1);
        spr.setPosition(x, y);
        checkCollision();
    }
    
    public Rectangle getArea() {
    	return spr.getBoundingRectangle();
    }
    public void draw(SpriteBatch batch) {
    	spr.draw(batch);
    }
    
    public void checkCollision() {
        ArrayList<Objeto> lista = ListaDeObjetos.getLista();
        if (lista == null) return;
        for (Objeto objeto : lista) {
            if (objeto == this) continue;
            if (this.getArea().overlaps(objeto.getArea())) {
                System.out.println("colision");
                // https://www.baeldung.com/java-method-reflection
                try {
                    Method method = this.getClass().getMethod("colisionado", objeto.getClass());
                    method.invoke(this, objeto);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    continue;
                }
            }
        }
    }

    public void colisionado(Asteroide asteroide) {
        if (getXSpeed() == 0) setXSpeed(getXSpeed() + asteroide.getXSpeed()/2);
        if (asteroide.getXSpeed() == 0) asteroide.setXSpeed(asteroide.getXSpeed() + getXSpeed()/2);
        setXSpeed(-getXSpeed());
        asteroide.setXSpeed(-asteroide.getXSpeed());

        if (getySpeed() == 0) setySpeed(getySpeed() + asteroide.getySpeed()/2);
        if (asteroide.getySpeed() == 0) asteroide.setySpeed(asteroide.getySpeed() + getySpeed()/2);
        setySpeed(- getySpeed());
        asteroide.setySpeed(- asteroide.getySpeed());
    }

    public void colisionado(Nave nave) {
        this.hp = 0;
    }

    public void colisionado(Proyectil proyectil) {
        this.quitarHp(proyectil.getDamage());
    }

    public boolean isDestroyed() {
        if (this.hp > 0) return false;
        return true;
    }

    public void quitarHp(int i) {
        this.hp = this.hp - i;
    }
	public int getXSpeed() {
		return xSpeed;
	}
	public void setXSpeed(int xSpeed) {
		this.xSpeed = xSpeed;
	}
	public int getySpeed() {
		return ySpeed;
	}
	public void setySpeed(int ySpeed) {
		this.ySpeed = ySpeed;
	}
}
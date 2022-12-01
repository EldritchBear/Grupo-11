package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public abstract class Elemento implements Colisionable {
    private Sprite spr;
    public Elemento(Sprite spr) {
        this.spr = spr;
    }
    public Elemento checkCollision() {
        return ElementosEnPantalla.checkCollision(this);
    }
    abstract public void colisionado(Elemento objeto);
    public Rectangle getArea() {
        return spr.getBoundingRectangle();
    }
    public void draw(SpriteBatch batch) {
        spr.draw(batch);
    }
    public void setRotation(float rot) {
        spr.setRotation(rot);
    }
    public void setPosition(float x, float y) {
        spr.setPosition(x, y);
    }
    public void setBounds(float x, float y, float w, float h) {
        spr.setBounds(x, y, w, h);
    }
    public void setX(float x) {
        spr.setX(x);
    }
    public void setY(float y) {
        spr.setY(y);
    }
    public float getX() {
        return spr.getX();
    }
    public float getY() {
        return spr.getY();
    }
    public float getWidth() {
        return spr.getWidth();
    }
    public float getHeight() {
        return spr.getHeight();
    }
    public void setSize(float w, float h) {
        spr.setSize(w, h);
    }
}
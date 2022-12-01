package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public abstract class ObjetoFisico implements Colisionable {
    protected Sprite spr;
    public ObjetoFisico(Sprite spr) {
        this.spr = spr;
    }
    public ObjetoFisico checkCollision() {
        return ObjetosEnPantalla.checkCollision(this);
    }
    abstract public void colisionado(ObjetoFisico objeto);
    public Rectangle getArea() {
        return spr.getBoundingRectangle();
    }
    public void draw(SpriteBatch batch) {
        spr.draw(batch);
    }
}
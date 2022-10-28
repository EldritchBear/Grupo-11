package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public interface Objeto {
    public void update();
    public boolean isDestroyed();
    public void checkCollision();
    public Rectangle getArea();
    public void draw(SpriteBatch batch);
    // debe implementar cada caso en donde el objeto colisiona con otro
//    public void colisionado(Object object);
    public void colisionado(Asteroide asteroide);
    public void colisionado(Nave nave);
    public void colisionado(Proyectil proyectil);
}

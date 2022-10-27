package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface Objeto {
    public void update();
    public boolean isDestroyed();
    public void checkCollision();
    // debe implementar cada caso en donde el objeto colisiona con otro
    public void colisionado(Asteroide asteroide);
    public void colisionado(Nave nave);
    public void colisionado(Proyectil proyectil);
    public void draw(SpriteBatch batch);
}

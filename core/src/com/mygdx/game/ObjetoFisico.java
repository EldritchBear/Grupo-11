package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public interface ObjetoFisico {
    public void update();
    public boolean isDestroyed();
    // debe implementar cada caso en donde el objeto colisiona con otro
    public void colisionado(Asteroide asteroide);
    public void colisionado(Nave nave);
    public void colisionado(Proyectil proyectil);
}
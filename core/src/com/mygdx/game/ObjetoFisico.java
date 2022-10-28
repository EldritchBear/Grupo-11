package com.mygdx.game;

public interface ObjetoFisico {
    void update();
    boolean isDestroyed();
    // debe implementar cada caso en donde el objeto colisiona con otro
    void colisionado(Asteroide asteroide);
    void colisionado(Nave nave);
    void colisionado(Proyectil proyectil);
}
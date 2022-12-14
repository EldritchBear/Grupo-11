package com.mygdx.game;

import com.mygdx.game.asteroides.Asteroide;
import com.mygdx.game.proyectil.Proyectil;

public interface Colisionable {
    void update();
    boolean isDestroyed();
    // debe implementar cada caso en donde el objeto colisiona con otro
    void colisionado(Elemento elemento);
    void colisionado(Asteroide asteroide);
    void colisionado(Nave nave);
    void colisionado(Proyectil proyectil);
}
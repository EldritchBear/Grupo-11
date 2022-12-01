package com.mygdx.game.nivel;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface Nivel {


    void render(SpriteBatch batch);

    void update();

    boolean esGameOver();

    boolean estaCompletado();

    void generarAsteroides();

    int getVidas();
}

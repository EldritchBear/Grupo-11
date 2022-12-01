package com.mygdx.game.nivel;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.ObjetosEnPantalla;
import com.mygdx.game.Nave;

public interface Nivel {


    void render(SpriteBatch batch);

    void update();

    boolean esGameOver();

    boolean estaCompletado();

    void generarAsteroides();

    int getVidas();
}

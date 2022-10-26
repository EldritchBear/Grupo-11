package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.util.Random;

public class NivelNormal implements Nivel{
    public Asteroides generarAsteroides(int cantAsteroides, int velXAsteroides, int velYAsteroides){
        Random r = new Random();
        Asteroides asteroidesNivel = new Asteroides();
        for (int i = 0; i < cantAsteroides; i++) {
            Asteroides bb = new Asteroides(r.nextInt(Gdx.graphics.getWidth()),
                    50+r.nextInt(Gdx.graphics.getHeight()-50),
                    20+r.nextInt(10), velXAsteroides+r.nextInt(4), velYAsteroides+r.nextInt(4),
                    new Texture(Gdx.files.internal("aGreyMedium4.png")),
                    2);
            asteroidesNivel.agregarAsteroides(bb);
        }
    }
}

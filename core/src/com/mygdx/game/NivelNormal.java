package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;
import java.util.Random;

public class NivelNormal extends Nivel {
    public void generarAsteroides(ArrayList objetos){
        int cantAsteroides = 5;
        int velXAsteroides = 1;
        int velYAsteroides = 1;
        Random r = new Random();
        for (int i = 0; i < cantAsteroides; i++) { // usar subclase de Asteroide
            Asteroides bb = new Asteroides(r.nextInt(Gdx.graphics.getWidth()),
                    50+r.nextInt(Gdx.graphics.getHeight()-50),
                    20+r.nextInt(10), velXAsteroides+r.nextInt(4),
                    velYAsteroides+r.nextInt(4),
                    new Texture(Gdx.files.internal("aGreyMedium4.png")), 2);
            asteroides.añadirAListas(bb);
        }
    }
}
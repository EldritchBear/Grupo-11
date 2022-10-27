package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.util.Random;

public class NivelNormal extends Nivel {
    public Asteroides generarAsteroides(){
        int cantAsteroides = 5;
        int velXAsteroides = 1;
        int velYAsteroides = 1;
        Random r = new Random();
        Asteroides asteroides = new Asteroides();
        for (int i = 0; i < cantAsteroides; i++) { // usar subclase de Asteroide
//            Asteroide bb = new Asteroide(r.nextInt(Gdx.graphics.getWidth()),
//                    50+r.nextInt(Gdx.graphics.getHeight()-50),
//                    20+r.nextInt(10), velXAsteroides+r.nextInt(4), velYAsteroides+r.nextInt(4),
//                    new Texture(Gdx.files.internal("aGreyMedium4.png")),
//                    2);
        }
        return asteroides;
    }

    public Nave getNave(){
        Nave aux = getNaveAbs();
        return aux;
    }
}

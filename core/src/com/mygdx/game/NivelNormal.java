package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;
import java.util.Random;

public class NivelNormal extends Nivel {
    public NivelNormal(){
        super();
    }
    public void generarAsteroides(ArrayList objetos){
        int cantAsteroides = 25;
        int velXAsteroides = 1;
        int velYAsteroides = 1;
        Random r = new Random();
        Texture textura = new Texture(Gdx.files.internal("aGreyMedium4.png"));
        for (int i = 0; i < cantAsteroides; i++) { // usar subclase de Asteroide
            Asteroide bb = new Asteroide(r.nextInt(Gdx.graphics.getWidth()),
                    50+r.nextInt(Gdx.graphics.getHeight()-50),
                    20+r.nextInt(10), velXAsteroides+r.nextInt(4),
                    velYAsteroides+r.nextInt(4),
                    textura, 2);
            objetos.add(bb);
        }
    }
}
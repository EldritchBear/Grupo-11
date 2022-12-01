package com.mygdx.game.nivel;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import asteroides.Asteroide;
import com.mygdx.game.ObjetosEnPantalla;

import java.util.Random;

public class NivelNormal extends Nivel {
    final private int nivel;
    public NivelNormal(int nivel){
        super();
        this.nivel = nivel;
    }
    public void generarAsteroides(){
        int cantAsteroides = 3 + (this.nivel);
        ObjetosEnPantalla.setNumAsteroides(cantAsteroides);
        int velXAsteroides = 1 + (this.nivel / 8);
        int velYAsteroides = 1 + (this.nivel / 8);
        Random r = new Random();
        Texture textura = new Texture(Gdx.files.internal("aGreyMedium4.png"));
        for (int i = 0; i < cantAsteroides; i++) {
            Asteroide bb = new Asteroide(r.nextInt(Gdx.graphics.getWidth()),
                    50+r.nextInt(Gdx.graphics.getHeight()-50),
                    20+r.nextInt(10), velXAsteroides+r.nextInt(4),
                    velYAsteroides+r.nextInt(4),
                    textura, 2);
            ObjetosEnPantalla.agregarObjeto(bb);
        }
    }
}
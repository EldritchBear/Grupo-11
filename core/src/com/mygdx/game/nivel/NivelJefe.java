package com.mygdx.game.nivel;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import asteroides.Asteroide;
import asteroides.AsteroideJefe;
import com.mygdx.game.ObjetosEnPantalla;

import java.util.Random;

public class NivelJefe extends Nivel {
    final private int nivel;
    public NivelJefe(int nivel) {
        super();
        this.nivel = nivel;
    }
    public void generarAsteroides(){
        int cantAsteroides = 4 + (this.nivel);
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
        for (int i = 0; i < this.nivel / 3 + 1; i++) {
            cantAsteroides += 1;
            ObjetosEnPantalla.agregarObjeto(new AsteroideJefe(r.nextInt(Gdx.graphics.getWidth()),
                    50+r.nextInt(Gdx.graphics.getHeight()-50),
                    20+r.nextInt(10), (velXAsteroides+r.nextInt(4)) * nivel/3,
                    (velYAsteroides+r.nextInt(4)) * nivel/3,
                    textura, 6 + nivel));
        }
    }
}
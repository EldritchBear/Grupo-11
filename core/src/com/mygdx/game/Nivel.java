package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

public abstract class Nivel {
    Nave nave;
    ArrayList objetos = new ArrayList();
    private int vidas = 3;
    Nivel () {}

    public void generarNivel() {
        nave = new Nave(Gdx.graphics.getWidth()/2-50,30,new Texture(Gdx.files.internal("MainShip3.png")),
                Gdx.audio.newSound(Gdx.files.internal("hurt.ogg")),
                new Texture(Gdx.files.internal("Rocket2.png")),
                Gdx.audio.newSound(Gdx.files.internal("pop-sound.mp3")));
        nave.setVidas(vidas);

        generarAsteroides(this.objetos);
    }

    public void render() {
        ArrayList destruidos = new ArrayList();
        for (Object objeto : objetos) {
            ((Objeto)objeto).update();
            if (((Objeto)objeto).isDestroyed()) {
                destruidos.add(objeto);
            }
        }
        for (Object objeto : destruidos) {
            objetos.remove(objeto);
        }
    }

    abstract void generarAsteroides(ArrayList objetos);
}

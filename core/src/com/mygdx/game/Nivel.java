package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

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

    public void render(SpriteBatch batch) {
        this.update();

        for (Object b : objetos) {
            ((Objeto)b).draw(batch);
        }
        nave.draw(batch);
    }

    public void update() {
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

    public void agregarBala(Proyectil bb) {
        objetos.add(bb);
    }

    public boolean esGameOver(){
        if(vidas == 0){
            return true;
        }
        return false;
    }

    public boolean estaCompletado(){
        if(objetos.isEmpty()){
            return true;
        }
        return false;
    }
    public int getVidas() {
        return this.nave.getVidas();
    }

    abstract void generarAsteroides(ArrayList objetos);
}

package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public abstract class Nivel {
    private Nave nave;
    private ArrayList<ObjetoColisionable> objetos;
    public Nivel() {
        this.objetos = new ArrayList();
        generarAsteroides(this.objetos);
        new ListaDeObjetos(objetos);
        this.nave = new Nave(Gdx.graphics.getWidth()/2-50,30,new Texture(Gdx.files.internal("MainShip3.png")),
                Gdx.audio.newSound(Gdx.files.internal("hurt.ogg")),
                new Texture(Gdx.files.internal("Rocket2.png")),
                Gdx.audio.newSound(Gdx.files.internal("pop-sound.mp3")));
                nave.setVidas(3);
        this.objetos.add(nave);
    }

    public void render(SpriteBatch batch) {
        this.update();
        for (Object b : objetos) {
            ((ObjetoColisionable)b).draw(batch);
        }
    }

    public void update() {
        ArrayList destruidos = new ArrayList();
        ArrayList colisionados = new ArrayList();
        for (ObjetoColisionable objeto : objetos) {
            objeto.update();
            if (!colisionados.contains(objeto)) {
                ObjetoColisionable objetoColisionado = objeto.checkCollision();
                if (objetoColisionado != null) {
                    colisionados.add(objeto);
                    colisionados.add(objetoColisionado);
                }
            }
            if (objeto.isDestroyed()) destruidos.add(objeto);
        }
        for (Object objeto : destruidos) {
            objetos.remove(objeto);
        }
        objetos.addAll(ListaDeObjetos.getCola());
        ListaDeObjetos.eliminarCola();
    }

    public void agregarBala(Proyectil bb) {
        objetos.add(bb);
    }

    public boolean esGameOver(){
        if(nave.getVidas() == 0){
            return true;
        }
        return false;
    }

    public boolean estaCompletado(){
        if(ListaDeObjetos.getNumAsteroides() == 0){
            return true;
        }
        return false;
    }
    public int getVidas() {
        if (this.nave == null) return 0;
        return this.nave.getVidas();
    }

    abstract void generarAsteroides(ArrayList objetos);
}
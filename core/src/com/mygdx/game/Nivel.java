package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public abstract class Nivel {
    final private Nave nave;
    final private ArrayList<ObjetoColisionable> objetos;
    public Nivel() {
        this.objetos = new ArrayList<>();
        generarAsteroides(this.objetos);
        ListaDeObjetos.actualizarLista(objetos);
        this.nave = new Nave(Gdx.graphics.getWidth()/2-50,30,new Texture(Gdx.files.internal("MainShip3.png")),
                Gdx.audio.newSound(Gdx.files.internal("hurt.ogg")));
                nave.setVidas(3);
        this.objetos.add(nave);
    }

    public void render(SpriteBatch batch) {
        this.update();
        for (ObjetoColisionable b : objetos) {
            b.draw(batch);
        }
    }

    public void update() {
        ArrayList<ObjetoColisionable> destruidos = new ArrayList<>();
        ArrayList<ObjetoColisionable> colisionados = new ArrayList<>();
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

    public boolean esGameOver(){
        return nave.getVidas() == 0;
    }

    public boolean estaCompletado(){
        return ListaDeObjetos.getNumAsteroides() == 0;
    }
    public int getVidas() {
        if (this.nave == null) return 0;
        return this.nave.getVidas();
    }

    abstract void generarAsteroides(ArrayList<ObjetoColisionable> objetos);
}
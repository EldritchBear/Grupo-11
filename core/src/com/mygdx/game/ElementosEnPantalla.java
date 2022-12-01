package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public class ElementosEnPantalla {
    private static ArrayList<Elemento> lista;
    private static ArrayList<Elemento> cola;
    private static ArrayList<Elemento> colaDestruidos;
    private static ArrayList<Elemento> objetosColisionados;
    private static int numAsteroides;
    private ElementosEnPantalla() {
        lista = new ArrayList<>();
        numAsteroides = 0;
        cola = new ArrayList<>();
        colaDestruidos = new ArrayList<>();
        limpiarColisionados();
    }
    public static void reducirAsteroides() {
        numAsteroides--;
    }
    public static int getNumAsteroides() {
//        if (lista == null) new ObjetosEnPantalla();
        return numAsteroides;
    }
    public static void setNumAsteroides(int num) {
        numAsteroides = num;
    }

    public static void agregarObjeto(Elemento objeto) {
        if (lista == null) new ElementosEnPantalla();
        lista.add(objeto);
    }

    public static void agregarDestruido(Elemento objeto) {
        colaDestruidos.add(objeto);
    }
    public static void limpiar() {
        new ElementosEnPantalla();
    }

    public static void limpiarCola() {
        lista.addAll(cola);
        cola = new ArrayList<>();
    }

    public static void limpiarColaDestruidos() {
        lista.removeAll(colaDestruidos);
        colaDestruidos = new ArrayList<>();
    }

    public static void agregarColisionado(Elemento objeto) {
        objetosColisionados.add(objeto);
    }

    public static void limpiarColisionados() {
        objetosColisionados = new ArrayList<>();
    }

    public static boolean revisarColision(Elemento objeto) {
        return objetosColisionados.contains(objeto);
    }

    public static void update() {
        for (Elemento objeto : lista) {
            objeto.update();
            if (!revisarColision(objeto)) {
                Elemento objetoColisionado = objeto.checkCollision();
                if (objetoColisionado != null) {
                    agregarColisionado(objeto);
                    agregarColisionado(objetoColisionado);
                }
            }
            if (objeto.isDestroyed()) agregarDestruido(objeto);
        }
        limpiarColaDestruidos();
        limpiarColisionados();
        limpiarCola();
    }

    public static void render(SpriteBatch batch) {
        for (Elemento b : lista) {
            b.draw(batch);
        }
    }

    public static void agregarACola(Elemento objeto) {
        cola.add(objeto);
    }

    public static Elemento checkCollision(Elemento objRecibido) {
        for (Elemento objeto : lista) {
            if (objeto == objRecibido) continue;
            if (objRecibido.getArea().overlaps(objeto.getArea())) {
                objRecibido.colisionado(objeto);
                objeto.colisionado(objRecibido);
                return objeto;
            }
        }
        return null;
    }
}
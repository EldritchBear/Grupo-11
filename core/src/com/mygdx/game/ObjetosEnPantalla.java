package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public class ObjetosEnPantalla {
    private static ArrayList<ObjetoFisico> lista;
    private static ArrayList<ObjetoFisico> cola;
    private static ArrayList<ObjetoFisico> colaDestruidos;
    private static ArrayList<ObjetoFisico> objetosColisionados;
    private static int numAsteroides;
    private ObjetosEnPantalla() {
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

    public static void agregarObjeto(ObjetoFisico objeto) {
        if (lista == null) new ObjetosEnPantalla();
        lista.add(objeto);
    }

    public static void agregarDestruido(ObjetoFisico objeto) {
        colaDestruidos.add(objeto);
    }
    public static void limpiar() {
        new ObjetosEnPantalla();
    }

    public static void limpiarCola() {
        lista.addAll(cola);
        cola = new ArrayList<>();
    }

    public static void limpiarColaDestruidos() {
        lista.removeAll(colaDestruidos);
        colaDestruidos = new ArrayList<>();
    }

    public static void agregarColisionado(ObjetoFisico objeto) {
        objetosColisionados.add(objeto);
    }

    public static void limpiarColisionados() {
        objetosColisionados = new ArrayList<>();
    }

    public static boolean revisarColision(ObjetoFisico objeto) {
        return objetosColisionados.contains(objeto);
    }

    public static void update() {
        for (ObjetoFisico objeto : lista) {
            objeto.update();
            if (!revisarColision(objeto)) {
                ObjetoFisico objetoColisionado = objeto.checkCollision();
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
        for (ObjetoFisico b : lista) {
            b.draw(batch);
        }
    }

    public static void agregarACola(ObjetoFisico objeto) {
        cola.add(objeto);
    }

    public static ObjetoFisico checkCollision(ObjetoFisico objRecibido) {
        for (ObjetoFisico objeto : lista) {
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
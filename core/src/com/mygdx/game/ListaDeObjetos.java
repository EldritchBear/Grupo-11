package com.mygdx.game;

import java.util.ArrayList;

public class ListaDeObjetos {
    private static ArrayList<ObjetoColisionable> lista;
    private static ArrayList<ObjetoColisionable> cola;
    private static int numAsteroides;
    public static void actualizarLista(ArrayList<ObjetoColisionable> list) {
        lista = list;
        numAsteroides = list.size();
        cola = new ArrayList<>();
    }
    private ListaDeObjetos() {
        lista = new ArrayList<>();
        numAsteroides = 0;
        cola = new ArrayList<>();
    }
    public static void eliminarCola() {
        cola = new ArrayList<>();
    }
    public static ArrayList<ObjetoColisionable> getLista() {
        if (lista == null) new ListaDeObjetos();
        return lista;
    }
    public static ArrayList<ObjetoColisionable> getCola() {
        if (cola == null) new ListaDeObjetos();
        return cola;
    }
    public static void reducirAsteroides() {
        numAsteroides--;
    }
    public static int getNumAsteroides() {
        if (lista == null) new ListaDeObjetos();
        return numAsteroides;
    }
}
package com.mygdx.game;

import java.util.ArrayList;

public class ListaDeObjetos {
    private static ArrayList<ObjetoColisionable> lista;
    private static ArrayList<ObjetoColisionable> cola;
    private static int numAsteroides;
    public ListaDeObjetos(ArrayList<ObjetoColisionable> list) {
        lista = list;
        numAsteroides = list.size();
        cola = new ArrayList<ObjetoColisionable>();
    }
    public static void eliminarCola() {
        cola = new ArrayList<ObjetoColisionable>();
    }
    public static ArrayList<ObjetoColisionable> getLista() {
        return lista;
    }
    public static ArrayList<ObjetoColisionable> getCola() {
        return cola;
    }
    public static void reducirAsteroides() {
        numAsteroides--;
    }
    public static int getNumAsteroides() {
        return numAsteroides;
    }
}
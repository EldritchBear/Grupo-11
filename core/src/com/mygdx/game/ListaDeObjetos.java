package com.mygdx.game;

import java.util.ArrayList;

public class ListaDeObjetos {
    private static ArrayList<Objeto> lista;
    private static ArrayList<Objeto> cola;
    public ListaDeObjetos(ArrayList<Objeto> list) {
        lista = list;
        cola = new ArrayList<Objeto>();
    }
    public static void eliminarCola() {
        cola = new ArrayList<Objeto>();
    }
    public static ArrayList<Objeto> getLista() {
        return lista;
    }
    public static ArrayList<Objeto> getCola() {
        return cola;
    }
}
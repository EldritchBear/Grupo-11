package com.mygdx.game;

public class Vidas {
    private int vidas;
    public Vidas(int vidas) {
        this.vidas = vidas;
    }
    public int getVidas() {
        return this.vidas;
    }
    public void reducirVidas(int num) {
        this.vidas -= num;
    }
    public void agregarVidas(int num) {
        this.vidas += num;
    }
}

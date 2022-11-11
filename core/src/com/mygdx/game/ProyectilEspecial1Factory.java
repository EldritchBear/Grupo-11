package com.mygdx.game;

public class ProyectilEspecial1Factory implements ProyectilFactory {
    public Proyectil crearProyectil(int x, int y, int vel, int rot, int dmg) {
        return new Proyectil(x, y, vel, rot, dmg);
    }
}

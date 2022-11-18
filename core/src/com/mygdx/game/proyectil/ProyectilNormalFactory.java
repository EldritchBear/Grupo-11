package com.mygdx.game.proyectil;

public class ProyectilNormalFactory implements ProyectilFactory{
    public Proyectil crearProyectil(int x, int y, int vel, int rot, int dmg) {
        return new Proyectil(x, y, vel, rot, dmg);
    }
}

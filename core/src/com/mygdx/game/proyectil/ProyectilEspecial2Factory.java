package com.mygdx.game.proyectil;

public class ProyectilEspecial2Factory implements ProyectilFactory {
    public Proyectil crearProyectil(int x, int y, int vel, int rot, int dmg) {
        return new ProyectilEspecial2(x, y, vel, rot, dmg);
    }
}

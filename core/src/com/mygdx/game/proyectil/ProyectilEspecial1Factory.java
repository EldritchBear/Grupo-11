package com.mygdx.game.proyectil;

public class ProyectilEspecial1Factory implements ProyectilFactory {
    @Override
    public Proyectil crearProyectil(float x, float y, int vel, int rot, int dmg) {
        return new ProyectilEspecial1(x, y, vel, rot, dmg);
    }
}

package com.mygdx.game.proyectil;

public class ProyectilEspecialFactory implements ProyectilFactory {
    @Override
    public Proyectil crearProyectil(float x, float y, int vel, int rot, int dmg) {
        return new ProyectilEspecial(x, y, vel, rot, dmg);
    }
}

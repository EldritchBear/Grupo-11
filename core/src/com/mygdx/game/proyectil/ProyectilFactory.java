package com.mygdx.game.proyectil;

public interface ProyectilFactory {
    public Proyectil crearProyectil(float x, float y, int vel, int rot, int dmg);
}

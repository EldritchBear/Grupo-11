package com.mygdx.game.proyectil;

public interface ProyectilFactory {
    Proyectil crearProyectil(float x, float y, int vel, int rot, int dmg);
}

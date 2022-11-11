package com.mygdx.game;

public interface ProyectilFactory {
    public Proyectil crearProyectil(int x, int y, int vel, int rot, int dmg);
}

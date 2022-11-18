package com.mygdx.game.proyectil;

public interface ProyectilFactory {
    public Proyectil crearProyectil(int x, int y, int vel, int rot, int dmg);
}

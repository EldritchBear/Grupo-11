package com.mygdx.game.proyectil;

public class ProyectilEspecial2Factory implements ProyectilFactory {
    @Override
    public Proyectil crearProyectil(float x, float y, int vel, int rot, int dmg) {
        return new ProyectilEspecial2(x, y, vel, rot, dmg);
    }
}




//es necesaria esta? usamos proyectil normal para los normales y especial1 para criticos simplemente.
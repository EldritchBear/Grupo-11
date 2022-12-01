package com.mygdx.game.proyectil;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class ProyectilNormalFactory implements ProyectilFactory{
    @Override
    public Proyectil crearProyectil(float x, float y, int vel, int rot, int dmg) {
        return new Proyectil(x, y, vel, rot, dmg, new Sprite(new Texture(Gdx.files.internal("Rocket2.png"))));
    }
}

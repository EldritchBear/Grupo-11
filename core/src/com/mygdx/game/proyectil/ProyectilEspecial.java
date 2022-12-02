package com.mygdx.game.proyectil;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.asteroides.Asteroide;

import java.util.ArrayList;

public class ProyectilEspecial extends Proyectil {
    ArrayList<Asteroide> asteroidesColisionados;
    public ProyectilEspecial(float x, float y, int velPr, int rr, int dmg){
        super(x, y, velPr, rr, dmg, new Sprite(new Texture(Gdx.files.internal("Rocket3.png"))));
        asteroidesColisionados = new ArrayList<>();
    }

    @Override
    public void colisionado(Asteroide asteroide) {
        asteroidesColisionados.add(asteroide);
    }

    @Override
    public boolean takeDamage(Asteroide asteroide) {
        return !asteroidesColisionados.contains(asteroide);
    }
}
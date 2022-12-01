package com.mygdx.game.proyectil;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class ProyectilEspecial1 extends Proyectil {
    public ProyectilEspecial1(float x, float y, int velPr, int rr, int dmg){
        super(x, y, velPr, rr, dmg, new Sprite(new Texture(Gdx.files.internal("Rocket3.png"))));
    }
}
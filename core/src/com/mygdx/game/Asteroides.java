package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.ArrayList;

public class Asteroides {
    final private ArrayList<Asteroides> balls1 = new ArrayList<>();
    final private ArrayList<Asteroides> balls2 = new ArrayList<>();

    public Asteroides(int x, int y, int size, int xSpeed, int ySpeed, Texture tx, int hp) {
        super();
    }

    public void añadirAListas(Asteroides bb){
        balls1.add(bb);
        balls2.add(bb);
    }

    public void removerDeLista(Asteroides bb){
        balls1.remove(bb);
        balls2.remove(bb);
    }

    public ArrayList getBalls(){
        return balls1;
    }
}
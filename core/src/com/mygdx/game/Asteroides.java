package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

public class Asteroides {
    final private ArrayList<Asteroide> balls1 = new ArrayList<>();
    final private ArrayList<Asteroide> balls2 = new ArrayList<>();

    public Asteroides(int x, int y, int size, int xSpeed, int ySpeed, Texture tx, int hp) {
        Asteroide bb = super(x,y,size,xSpeed,ySpeed,tx,hp);
        balls1 = new ArrayList<>();
        balls2 = new ArrayList<>();
    }

    public void agregarAsteroides(Asteroide bb) {
        balls1.add(bb);
        balls2.add(bb);
    }
}

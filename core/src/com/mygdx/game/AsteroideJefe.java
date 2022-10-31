package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class AsteroideJefe extends Asteroide{
    private int x;
    private int y;
    private int colisionCD;

    public AsteroideJefe(int x, int y, int size, int xSpeed, int ySpeed, Texture texture, int hp){
        super(x,y,size,xSpeed,ySpeed,texture,hp+3);
        spr.setSize(x,y);

    }
    public void update(){
        x += getXSpeed() * 2;
        y += getySpeed() * 2;

        if (x+getXSpeed() < 0 || x+getXSpeed()+spr.getWidth() > Gdx.graphics.getWidth())
            setXSpeed(getXSpeed() * -1);
        if (y+getySpeed() < 0 || y+getySpeed()+spr.getHeight() > Gdx.graphics.getHeight())
            setySpeed(getySpeed() * -1);
        spr.setPosition(x, y);
        if (colisionCD > 0) colisionCD--;
    }
}

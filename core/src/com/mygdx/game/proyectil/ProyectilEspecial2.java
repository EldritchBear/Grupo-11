package com.mygdx.game.proyectil;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.Asteroide;
import com.mygdx.game.AsteroideJefe;
import com.mygdx.game.Nave;

public class ProyectilEspecial2 extends Proyectil {
    private float xSpeed;
    private float ySpeed;
    private boolean destroyed = false;
    //private int velPr;                     //cambiar xspeed y yspeed por esto?
    final private int dmg;
    private int rotacion;

    public ProyectilEspecial2(float x, float y, int velPr, int rr, int dmg){
        super(x, y, velPr, rr, dmg);
        this.dmg = dmg;
        spr = new Sprite(new Texture(Gdx.files.internal("Rocket2.png")));
    }
    public void update() {
        spr.setPosition(spr.getX()+xSpeed, spr.getY()+ySpeed);
        if (spr.getX() < 0 || spr.getX()+spr.getWidth() > Gdx.graphics.getWidth()) {
            destroyed = true;
        }
        if (spr.getY() < 0 || spr.getY()+spr.getHeight() > Gdx.graphics.getHeight()) {
            destroyed = true;
        }
    }
    public void colisionado(Asteroide asteroide) {
        this.destroyed = true;
    }
    public void colisionado(AsteroideJefe asteroide) {
        colisionado((Asteroide)asteroide);
    }
    public void colisionado(Nave nave) {
        // nada
    }
    public void colisionado(Proyectil proyectil) {
        // nada
    }
    public boolean isDestroyed() {return destroyed;}
    public int getDamage() {
        return this.dmg;
    }
}
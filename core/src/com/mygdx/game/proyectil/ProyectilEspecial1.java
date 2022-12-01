package com.mygdx.game.proyectil;

import asteroides.Asteroide;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.Nave;

public class ProyectilEspecial1 extends Proyectil {
    private float xSpeed;
    private float ySpeed;
    private boolean destroyed = false;
    //private int velPr;                     //cambiar xspeed y yspeed por esto?
    final private int dmg;
    private int rotacion;

    public ProyectilEspecial1(float x, float y, int velPr, int rr, int dmg){
        super(x, y, velPr, rr, dmg, new Sprite(new Texture(Gdx.files.internal("Rocket2.png"))));
        this.dmg = dmg;
//        Texture tx = new Texture(Gdx.files.internal("Rocket2.png"));
//        spr = new Sprite(tx);
//        this.rotacion = rr;
//        this.dmg = dmg;
//        //this.velPr = velPr;
//        setRotation(rotacion);
//
//        this.rotacion += 90;
//
//        this.xSpeed = velPr * (float)(Math.cos(Math.toRadians(rotacion)));
//        this.ySpeed = velPr * (float)(Math.sin(Math.toRadians(rotacion)));
//
//        setPosition(x+xSpeed, y+ySpeed);
    }
    public void update() {
        setPosition(getX()+xSpeed, getY()+ySpeed);
        if (getX() < 0 || getX()+getWidth() > Gdx.graphics.getWidth()) {
            destroyed = true;
        }
        if (getY() < 0 || getY()+getHeight() > Gdx.graphics.getHeight()) {
            destroyed = true;
        }
    }
    public void colisionado(Asteroide asteroide) {
        this.destroyed = true;
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
package com.mygdx.game.proyectil;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.asteroides.Asteroide;
import com.mygdx.game.Nave;
import com.mygdx.game.Elemento;

public class Proyectil extends Elemento {
	private final float xSpeed;
	private final float ySpeed;
	private boolean destroyed = false;
	//private int velPr;                     //cambiar xspeed y yspeed por esto?
	final private int dmg;
	private int rotacion;

	public Proyectil(float x, float y, int velPr, int rr, int dmg, Sprite spr){
		super(spr);
		this.rotacion = rr;
		this.dmg = dmg;
		setRotation(rotacion);

		this.rotacion += 90;

		this.xSpeed = velPr * (float)(Math.cos(Math.toRadians(rotacion)));
		this.ySpeed = velPr * (float)(Math.sin(Math.toRadians(rotacion)));

		setPosition(x+xSpeed, y+ySpeed);
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
	public void colisionado(Elemento objeto) {
		objeto.colisionado(this);
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
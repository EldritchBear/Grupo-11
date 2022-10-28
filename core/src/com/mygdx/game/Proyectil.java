package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;

import java.lang.reflect.Method;
import java.util.ArrayList;


public class Proyectil implements Objeto {
	private float xSpeed;
	private float ySpeed;
	private boolean destroyed = false;
	final private Sprite spr;
	//private int velPr;                     //cambiar xspeed y yspeed por esto?
	private int dmg;

	private int rotacion;

	public Proyectil(float x, float y, int velPr, int rr, int dmg){
		Texture tx = new Texture(Gdx.files.internal("Rocket2.png"));
		spr = new Sprite(tx);
		this.rotacion = rr;
		this.dmg = dmg;
		//this.velPr = velPr;
		spr.setRotation(rotacion); //probar con grados y no radianes, creo que va a ser más sano
		if(Math.sin(Math.toRadians(rotacion)) > 0 && Math.sin(Math.toRadians(rotacion)) < 1) {
			this.xSpeed = -velPr + (float)(Math.sin(Math.toRadians(rotacion)));
			this.ySpeed = velPr - (float) (Math.cos((Math.toRadians(rotacion)))*0.1);
		}else if (Math.sin(Math.toRadians(rotacion)) < 1 && Math.sin(Math.toRadians(rotacion)) < 0){
			this.xSpeed = velPr - (float) (Math.sin(Math.toRadians(rotacion)));
			this.ySpeed = velPr + (float) (Math.cos(Math.toRadians(rotacion)));
		}else if (Math.sin(Math.toRadians(rotacion)) == 0){
			this.xSpeed = 0;
			this.ySpeed = velPr;
		}

		spr.setPosition(x, y);
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
	public void draw(SpriteBatch batch) {
		spr.draw(batch);
	}
	public Objeto checkCollision() {
		ArrayList<Objeto> lista = ListaDeObjetos.getLista();
		for (Objeto objeto : lista) {
			if (objeto == this) continue;
			if (this.getArea().overlaps(objeto.getArea())) {
				// https://www.baeldung.com/java-method-reflection
				try {
					Method method = this.getClass().getMethod("colisionado", objeto.getClass());
					method.invoke(this, objeto);
					objeto.colisionado(this);
					return objeto;
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
		}
		return null;
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
	public Rectangle getArea() {
		return spr.getBoundingRectangle();
	}
}
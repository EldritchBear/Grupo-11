package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class Proyectil {

	final private int xSpeed;
	final private int ySpeed;
	private boolean destroyed = false;
	final private Sprite spr;
	    
	    public Proyectil(float x, float y, int xSpeed, int ySpeed, Texture tx) {
	    	spr = new Sprite(tx);
	    	spr.setPosition(x, y);
	        this.xSpeed = xSpeed;
	        this.ySpeed = ySpeed;
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

		public boolean checkCollision(Asteroide b2) {
			if(spr.getBoundingRectangle().overlaps(b2.getArea())){
				// Se destruyen ambos
				this.destroyed = true;
				b2.quitarHp(1);
				return true;

			}
			return false;
		}
	    
	    public boolean isDestroyed() {return destroyed;}
	
}

package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;



public class Nave {
	
	private boolean destruida = false;
    private int vidas = 3;
    private float xVel = 0;
    private float yVel = 0;
    final private Sprite spr;
    final private Sound sonidoHerido;
    final private Sound soundBala;
    final private Texture txBala;
    private boolean herido = false;
    final private int tiempoHeridoMax=50;
    private int tiempoHerido;
    private int rotacion = 0;

    private int cdBala;

    final private int maxCd = 40;
    
    public Nave(int x, int y, Texture tx, Sound soundChoque, Texture txBala, Sound soundBala) {
    	sonidoHerido = soundChoque;
    	this.soundBala = soundBala;
    	this.txBala = txBala;
    	spr = new Sprite(tx);
    	spr.setPosition(x, y);
    	//spr.setOriginCenter();
    	spr.setBounds(x, y, 45, 45);

    }
    public void draw(SpriteBatch batch, PantallaJuego juego) {
        float x = spr.getX();
        float y = spr.getY();
        if (!herido) {
            // que se mueva con teclado
            //if (Gdx.input.isKeyPressed(Input.Keys.A)) xVel -= 0.1;
            //if (Gdx.input.isKeyPressed(Input.Keys.D)) xVel += 0.1;
            //if (Gdx.input.isKeyPressed(Input.Keys.S)) yVel -= 0.1;
            //if (Gdx.input.isKeyPressed(Input.Keys.W)) yVel += 0.1;

            if (Gdx.input.isKeyPressed(Input.Keys.A)) spr.setRotation(++rotacion);
            if (Gdx.input.isKeyPressed(Input.Keys.D)) spr.setRotation(--rotacion);

            if (Gdx.input.isKeyPressed(Input.Keys.W)) {
                xVel -= Math.sin(Math.toRadians(rotacion)) * 0.1;
                if (xVel > 1) {
                    xVel = 1;
                }
                yVel += Math.cos(Math.toRadians(rotacion)) * 0.1;
                if (yVel > 2.25f) {
                    yVel = 2.25f;
                }
                System.out.println(rotacion + " - " + Math.sin(Math.toRadians(rotacion)) + " - " + Math.cos(Math.toRadians(rotacion)));
            }
            if (Gdx.input.isKeyPressed(Input.Keys.S)) {
                xVel += Math.sin(Math.toRadians(rotacion)) * 0.1;
                if (xVel > 1) {
                    xVel = 1;
                }
                yVel -= Math.cos(Math.toRadians(rotacion)) * 0.1;
                if (yVel < -2.25f) {
                    yVel = -2.25f;
                }

            }

            // que se mantenga dentro de los bordes de la ventana
            if (x + xVel < 0 || x + xVel + spr.getWidth() > Gdx.graphics.getWidth())
                xVel *= -1;
            if (y + yVel < 0 || y + yVel + spr.getHeight() > Gdx.graphics.getHeight())
                yVel *= -1;

            spr.setPosition(x + xVel, y + yVel);

            spr.draw(batch);
        } else {
            spr.setX(spr.getX() + MathUtils.random(-2, 2));
            spr.draw(batch);
            spr.setX(x);
            tiempoHerido--;
            if (tiempoHerido <= 0) herido = false;
        }
        // disparo
        if (cdBala == 0) {
            if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                Proyectil bala = new Proyectil(spr.getX() + spr.getWidth() / 2 - 5, spr.getY() + spr.getHeight() - 5, 3, 3, txBala, rotacion);
                juego.agregarBala(bala);
                soundBala.play(0.3f);
                cdBala = maxCd;
            }
        }else{cdBala--;}
       
    }
      
    public boolean checkCollision(Asteroide b) {
        if(!herido && b.getArea().overlaps(spr.getBoundingRectangle())){
        	// rebote
            if (xVel ==0) xVel += b.getXSpeed()/2;
            if (b.getXSpeed() ==0) b.setXSpeed(b.getXSpeed() + (int)xVel/2);
            xVel = - xVel;
            b.setXSpeed(-b.getXSpeed());
            
            if (yVel ==0) yVel += b.getySpeed()/2;
            if (b.getySpeed() ==0) b.setySpeed(b.getySpeed() + (int)yVel/2);
            yVel = - yVel;
            b.setySpeed(- b.getySpeed());
            // despegar sprites
      /*      int cont = 0;
            while (b.getArea().overlaps(spr.getBoundingRectangle()) && cont<xVel) {
               spr.setX(spr.getX()+Math.signum(xVel));
            }   */
        	//actualizar vidas y herir
            vidas--;
            herido = true;
  		    tiempoHerido=tiempoHeridoMax;
  		    sonidoHerido.play();
            if (vidas<=0) 
          	    destruida = true; 
            return true;
        }
        return false;
    }
    
    public boolean estaDestruido() {
       return !herido && destruida;
    }
    public boolean estaHerido() {
 	   return herido;
    }
    
    public int getVidas() {return vidas;}
//    public boolean isDestruida() {return destruida;}
//    public int getX() {return (int) spr.getX();}
//    public int getY() {return (int) spr.getY();}
	public void setVidas(int vidas2) {vidas = vidas2;}
}

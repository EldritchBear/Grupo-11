package com.mygdx.game;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class PantallaJuego implements Screen {
	final private SpaceNavigation game;
	final private SpriteBatch batch;
	final private Sound explosionSound;
	final private Music gameMusic;
	private int score;
	final private int ronda;
	final private int velXAsteroides;
	final private int velYAsteroides;
	final private int cantAsteroides;
	final private Nave nave;

	final private asteroides nivel = new asteroides();
	final private  ArrayList<Proyectil> balas = new ArrayList<>();


	public PantallaJuego(SpaceNavigation game, int ronda, int vidas, int score,  
			int velXAsteroides, int velYAsteroides, int cantAsteroides) {
		this.game = game;
		this.ronda = ronda;
		this.score = score;
		this.velXAsteroides = velXAsteroides;
		this.velYAsteroides = velYAsteroides;
		this.cantAsteroides = cantAsteroides;
		
		batch = game.getBatch();
		OrthographicCamera camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 640);
		//inicializar assets; musica de fondo y efectos de sonido
		explosionSound = Gdx.audio.newSound(Gdx.files.internal("explosion.ogg"));
		explosionSound.setVolume(1,0.3f);
		//gameMusic = Gdx.audio.newMusic(Gdx.files.internal("piano-loops.wav"));
		gameMusic = Gdx.audio.newMusic(Gdx.files.internal("Puddles.wav"));
		
		gameMusic.setLooping(true);
		gameMusic.setVolume(0.3f);
		gameMusic.play();
		
	    // ya está en clase Nivel
	    nave = new Nave(Gdx.graphics.getWidth()/2-50,30,new Texture(Gdx.files.internal("MainShip3.png")),
	    				Gdx.audio.newSound(Gdx.files.internal("hurt.ogg")));
        nave.setVidas(vidas);
        // crea asteroides, implementarlo en subclases de Nivel
        Random r = new Random();
	    for (int i = 0; i < cantAsteroides; i++) {
	        Asteroides bb = new Asteroides(r.nextInt(Gdx.graphics.getWidth()),
	  	            50+r.nextInt(Gdx.graphics.getHeight()-50),
	  	            20+r.nextInt(10), velXAsteroides+r.nextInt(4), velYAsteroides+r.nextInt(4), 
	  	            new Texture(Gdx.files.internal("aGreyMedium4.png")),
					2);
			nivel.agregarAsteroide(bb);
	  	}
	}
    
	public void dibujaEncabezado() {
		CharSequence str = "Vidas: "+nave.getVidas()+" Ronda: "+ronda;
		game.getFont().getData().setScale(2f);		
		game.getFont().draw(batch, str, 10, 30);
		game.getFont().draw(batch, "Score:"+this.score, Gdx.graphics.getWidth()-150, 30);
		game.getFont().draw(batch, "HighScore:"+game.getHighScore(), Gdx.graphics.getWidth()/2-100, 30);
	}
	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		dibujaEncabezado();
		if (!nave.estaHerido()) {
			// colisiones entre balas y asteroides y su destruccion
			for (int i = 0; i < balas.size(); i++) {
				Proyectil b = balas.get(i);
				b.update();
				for (Asteroide ball : balls1) {
					if (b.checkCollision(ball)) {
						explosionSound.play(0.15f);
						if (ball.getHp() == 0) {
							balls1.remove(ball);
							balls2.remove(ball);
							score +=10;
							break;
						}
					}
				}

				//   b.draw(batch);
				if (b.isDestroyed()) {
					balas.remove(b);
					i--; //para no saltarse 1 tras eliminar del arraylist
				}
		      }
		      //actualizar movimiento de asteroides dentro del area
		      for (Asteroide ball : balls1) {
		          ball.update();
		      }
		      //colisiones entre asteroides y sus rebotes  
		      for (int i=0;i<balls1.size();i++) {
		    	Asteroide ball1 = balls1.get(i);
		        for (int j=0;j<balls2.size();j++) {
		          Asteroide asteroide = balls2.get(j);
		          if (i<j) {
		        	  ball1.checkCollision(asteroide);
		     
		          }
		        }
		      } 
	      }
	      //dibujar balas
	     for (Proyectil b : balas) {
	          b.draw(batch);
	      }
	      nave.draw(batch, this);
	      //dibujar asteroides y manejar colision con nave
	      for (int i = 0; i < balls1.size(); i++) {
	    	    Asteroide b=balls1.get(i);
	    	    b.draw(batch);
		          //perdió vida o game over
	              if (nave.checkCollision(b)) {
		            //asteroide se destruye con el choque             
	            	 balls1.remove(i);
	            	 balls2.remove(i);
	            	 i--;
              }   	  
  	        }
	      
	      if (nave.estaDestruido()) {
  			if (score > game.getHighScore())
  				game.setHighScore(score);
	    	Screen ss = new PantallaGameOver(game);
  			ss.resize(1200, 800);
  			game.setScreen(ss);
  			dispose();
  		  }
	      batch.end();
	      //nivel completado
	      if (balls1.size()==0) {
			Screen ss = new PantallaJuego(game,ronda+1, nave.getVidas(), score, 
					velXAsteroides+3, velYAsteroides+3, cantAsteroides+10);
			ss.resize(1200, 800);
			game.setScreen(ss);
			dispose();
		  }
	    	 
	}
    
    public void agregarBala(Proyectil bb) {
		balas.add(bb);
    }
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		gameMusic.play();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		this.explosionSound.dispose();
		this.gameMusic.dispose();
	}
   
}

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
	private Nivel nivel = new NivelNormal();

	public PantallaJuego(SpaceNavigation game, int ronda, int vidas, int score) {
		this.game = game;
		this.ronda = ronda;
		this.score = score;
		
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

	}

	public void dibujaEncabezado() {
		CharSequence str = "Vidas: "+nivel.getVidas()+" Nivel: "+ronda;
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
		//dibujar balas
		//////// mover a Nivel
		for (Proyectil b : balas) {
		  b.draw(batch);
		}
		nave.draw(batch, this);
		////////

		//el jugador se quedo sin vidas
		if (nivel.esGameOver()) {
		if (score > game.getHighScore())
			game.setHighScore(score);
		Screen ss = new PantallaGameOver(game);
		ss.resize(1200, 800);
		game.setScreen(ss);
		dispose();
		}
		batch.end();
		//nivel completado
		if (nivel.estaCompletado()) {
		Screen ss = new PantallaJuego(game,ronda+1, nivel.getVidas(), score);
		ss.resize(1200, 800);
		game.setScreen(ss);
		dispose();
		}

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

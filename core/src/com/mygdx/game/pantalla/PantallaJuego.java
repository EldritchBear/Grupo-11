package com.mygdx.game.pantalla;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.SpaceNavigation;
import com.mygdx.game.Vidas;
import com.mygdx.game.nivel.Nivel;
import com.mygdx.game.nivel.NivelJefe;
import com.mygdx.game.nivel.NivelNormal;


public class PantallaJuego implements Screen {
	final private SpaceNavigation game;
	final private SpriteBatch batch;
	final private Sound explosionSound;
	final private Music gameMusic;
	final private int ronda;
	final private Nivel nivel;
	Vidas vidas;

	public PantallaJuego(SpaceNavigation game, int ronda, Vidas vidas) {
		this.game = game;
		this.ronda = ronda;
		this.vidas = vidas;

		this.nivel = generarNivel(ronda);
		
		batch = game.getBatch();
		OrthographicCamera camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 640);
		//inicializar assets; musica de fondo y efectos de sonido
		explosionSound = Gdx.audio.newSound(Gdx.files.internal("explosion.ogg"));
		explosionSound.setVolume(1,0.3f);
		gameMusic = Gdx.audio.newMusic(Gdx.files.internal("piano-loops.wav"));
		//gameMusic = Gdx.audio.newMusic(Gdx.files.internal("Puddles.wav"));
		
		gameMusic.setLooping(true);
		gameMusic.setVolume(0.3f);
		gameMusic.play();
	}

	public void dibujaEncabezado() {
		CharSequence str = "Vidas: "+ nivel.getVidas()+" Nivel: "+ronda;
		game.getFont().getData().setScale(2f);
		game.getFont().draw(batch, str, 10, 30);
	}
	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		dibujaEncabezado();

		nivel.render(batch);

		//el jugador se quedo sin vidas
		if (nivel.esGameOver()) {
			Screen ss = new PantallaGameOver(game);
			ss.resize(1200, 800);
			game.setScreen(ss);
			dispose();
		}
		batch.end();
		//nivel completado
		if (nivel.estaCompletado()) {
			Screen ss = new PantallaJuego(game,ronda+1, vidas);
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
	
	public Nivel generarNivel(int ronda) {
		if (ronda % 3 != 0) return new NivelNormal(ronda, vidas);
		return new NivelJefe(ronda, vidas);
	}
   
}

package com.mygdx.game.nivel;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Nave;
import com.mygdx.game.Vidas;
import com.mygdx.game.asteroides.Asteroide;
import com.mygdx.game.asteroides.AsteroideJefe;
import com.mygdx.game.ElementosEnPantalla;

import java.util.Random;

public class NivelJefe implements Nivel {
    final private int nivel;
    final private Nave nave;
    public NivelJefe(int nivel, Vidas vidas) {
        vidas.agregarVidas(1);
        ElementosEnPantalla.limpiar();
        this.nivel = nivel;
        generarAsteroides();
        this.nave = new Nave(Gdx.graphics.getWidth()/2-50,30,new Texture(Gdx.files.internal("MainShip3.png")),
                Gdx.audio.newSound(Gdx.files.internal("hurt.ogg")), vidas);
        ElementosEnPantalla.agregarObjeto(nave);
    }
    public void generarAsteroides(){
        int cantAsteroides = (this.nivel);
        int cantAsteroidesJefe = this.nivel / 3;
        ElementosEnPantalla.setNumAsteroides(cantAsteroides + cantAsteroidesJefe);
        int velXAsteroides = 1 + (this.nivel / 8);
        int velYAsteroides = 1 + (this.nivel / 8);
        Random r = new Random();
        Texture textura = new Texture(Gdx.files.internal("aGreyMedium4.png"));
        for (int i = 0; i < cantAsteroides; i++) {
            Asteroide bb = new Asteroide(r.nextInt(Gdx.graphics.getWidth()),
                    50+r.nextInt(Gdx.graphics.getHeight()-50),
                    20+r.nextInt(10), velXAsteroides+r.nextInt(4),
                    velYAsteroides+r.nextInt(4),
                    textura, 2);
            ElementosEnPantalla.agregarObjeto(bb);
        }
        for (int i = 0; i < cantAsteroidesJefe; i++) {
            cantAsteroides += 1;
            Texture texturaJefe = new Texture(Gdx.files.internal("aGreyLarge.png"));
            ElementosEnPantalla.agregarObjeto(new AsteroideJefe(r.nextInt(Gdx.graphics.getWidth()),
                    50+r.nextInt(Gdx.graphics.getHeight()-50),
                    200+r.nextInt(50), velXAsteroides+r.nextInt(4),
                    velYAsteroides+r.nextInt(4),
                    texturaJefe, 6 + nivel));
        }
    }

    @Override
    public void update() {
        ElementosEnPantalla.update();
    }

    @Override
    public void render(SpriteBatch batch) {
        this.update();
        ElementosEnPantalla.render(batch);
    }

    public boolean esGameOver(){
        return nave.getVidas() == 0;
    }

    public boolean estaCompletado(){
        return ElementosEnPantalla.getNumAsteroides() == 0;
    }
    public int getVidas() {
        if (this.nave == null) return 0;
        return this.nave.getVidas();
    }
}
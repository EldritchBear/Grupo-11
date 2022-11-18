package com.mygdx.game.nivel;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.ObjetosEnPantalla;
import com.mygdx.game.Nave;

public abstract class Nivel {
    final private Nave nave;
    public Nivel() {
        generarAsteroides();
        this.nave = new Nave(Gdx.graphics.getWidth()/2-50,30,new Texture(Gdx.files.internal("MainShip3.png")),
                Gdx.audio.newSound(Gdx.files.internal("hurt.ogg")));
                nave.setVidas(3);
        ObjetosEnPantalla.agregarObjeto(nave);
    }

    public void render(SpriteBatch batch) {
        this.update();
        ObjetosEnPantalla.render(batch);
    }

    public void update() {

        ObjetosEnPantalla.update();
    }

    public boolean esGameOver(){
        return nave.getVidas() == 0;
    }

    public boolean estaCompletado(){
        return ObjetosEnPantalla.getNumAsteroides() == 0;
    }
    public int getVidas() {
        if (this.nave == null) return 0;
        return this.nave.getVidas();
    }

    abstract void generarAsteroides();
}
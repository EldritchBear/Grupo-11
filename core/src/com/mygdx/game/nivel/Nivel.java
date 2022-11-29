package com.mygdx.game.nivel;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Asteroide;
import com.mygdx.game.AsteroideJefe;
import com.mygdx.game.ObjetosEnPantalla;
import com.mygdx.game.Nave;

import java.util.Random;

public class Nivel {
    final private Nave nave;

    final private int nivel;
    public Nivel(int nn) {
        this.nivel = nn;
        if (nivel % 3 != 0) generarAsteroides();
        else {generarJefe();}

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

    public void generarAsteroides(){
        int cantAsteroides = 1 + (this.nivel);
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
            ObjetosEnPantalla.agregarObjeto(bb);
        }
    }

    public void generarJefe(){
        int cantAsteroides = 4 + (this.nivel);
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
            ObjetosEnPantalla.agregarObjeto(bb);
        }
        for (int i = 0; i < this.nivel / 3 + 1; i++) {
            cantAsteroides += 1;
            System.out.println("asteroide jefe");
            ObjetosEnPantalla.agregarObjeto(new AsteroideJefe(r.nextInt(Gdx.graphics.getWidth()),
                    50+r.nextInt(Gdx.graphics.getHeight()-50),
                    20+r.nextInt(10), (velXAsteroides+r.nextInt(4)) * nivel/3,
                    (velYAsteroides+r.nextInt(4)) * nivel/3,
                    textura, 6 + nivel));
        }
    }
}
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.mygdx.game.proyectil.Proyectil;
import com.mygdx.game.proyectil.ProyectilEspecialFactory;
import com.mygdx.game.proyectil.ProyectilFactory;
import com.mygdx.game.proyectil.ProyectilNormalFactory;

import java.util.Random;

public class Armamento {

    private final int dmg;

    private final int velAt;        //esto define el cooldown (cdBala)

    private final int velPr;

    private int cdBala;

    final private Sound soundBala;

    final int critChance;    //este valor se tiene que ir reduciendo para aumentar la probabilidad.

    public Armamento(int dd, int vA, int vP, int cC){
        this.dmg = dd;
        this.velAt = vA;
        this.velPr = vP;
        this.critChance = cC;
        soundBala = Gdx.audio.newSound(Gdx.files.internal("pop-sound.mp3"));
    }

    public void disparar(float x, float y, int rotacion){
        ProyectilFactory factory;
        Proyectil bala;

        if (cdBala == 0) {
            Random rand = new Random();
            int crit = rand.nextInt(101);
            if (crit < critChance) factory = new ProyectilEspecialFactory();
            else factory = new ProyectilNormalFactory();
            bala = factory.crearProyectil(x,y,velPr,rotacion,dmg);
            cdBala = velAt;
            soundBala.play(0.3f);
            ElementosEnPantalla.agregarACola(bala);
        }
    }
    public void disminuirCdBala() {
        if (cdBala > 0) cdBala--;
    }
}

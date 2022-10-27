package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class Armamento {

    private int dmg;

    private int velAt;        //esto define el cooldown (cdBala)

    private int velPr;

    private int cdBala;

    final private Sound soundBala;

    public Armamento(int dd, int vA, int vP){
        this.dmg = dd;
        this.velAt = vA;
        this.velPr = vP;
        soundBala = Gdx.audio.newSound(Gdx.files.internal("pop-sound.mp3"));
    }

    public void disparar(float x, float y, int rotacion){
        if (cdBala == 0){
            Proyectil bala = new Proyectil(x, y, velPr, rotacion,dmg,velAt);
            cdBala = velAt;
            soundBala.play(0.3f);
        }else{cdBala--;}
    }

    public int getDmg() {
        return dmg;
    }

    public int getVelPr() {
        return velPr;
    }

    public int getVelAt(){
        return velAt;
    }

    public void setDmg(int dd){
        this.dmg = dd;
    }

    public void setVelAt(int velAt) {
        this.velAt = velAt;
    }

    public void setVelPr(int velPr) {
        this.velPr = velPr;
    }
}

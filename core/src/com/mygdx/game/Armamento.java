package com.mygdx.game;

public class Armamento {

    private int dmg;

    private int velAt;        //esto define el cooldown (cdBala)

    private int velPr;

    private int cdBala;

    public Armamento(int dd, int vA, int vP){
        this.dmg = dd;
        this.velAt = vA;
        this.velPr = vP;
    }


}

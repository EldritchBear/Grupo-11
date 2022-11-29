package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import java.lang.reflect.Method;
import java.util.ArrayList;

public abstract class Elemento implements Colisionable {
    public Elemento checkCollision() {
        ArrayList<Elemento> lista = ObjetosEnPantalla.getLista();
        for (Elemento objeto : lista) {
            if (objeto == this) continue;
            if (this.getArea().overlaps(objeto.getArea())) {
                // https://www.baeldung.com/java-method-reflection
                try {
                    Method method = this.getClass().getMethod("colisionado", objeto.getClass());
                    method.invoke(this, objeto);

                    Method methodColisionado = objeto.getClass().getMethod("colisionado", this.getClass());
                    methodColisionado.invoke(objeto, this);
                    return objeto;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        return null;
    }
    public Rectangle getArea() {
        return spr.getBoundingRectangle();
    }
    public void draw(SpriteBatch batch) {
        spr.draw(batch);
    }
}
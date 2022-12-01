package asteroides;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class AsteroideJefe extends Asteroide {
    private int x;
    private int y;
    private int colisionCD;

    public AsteroideJefe(int x, int y, int size, int xSpeed, int ySpeed, Texture texture, int hp){
        super(x,y,size,xSpeed,ySpeed,texture,hp);
        setSize(size,size);
        System.out.println(xSpeed);
//        setBounds(x, y, 300, 300);
    }
    public void update(){
        x += getXSpeed() * 2;
        y += getySpeed() * 2;

        if (x+getXSpeed() < 0 || x+getXSpeed()+getWidth() > Gdx.graphics.getWidth())
            setXSpeed(getXSpeed() * -1);
        if (y+getySpeed() < 0 || y+getySpeed()+getHeight() > Gdx.graphics.getHeight())
            setySpeed(getySpeed() * -1);
        setPosition(x, y);
        if (colisionCD > 0) colisionCD--;
    }
}
package com.mygdx.game.Entities;

import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Andreas on 16.04.2016.
 */
public abstract class Entity {

    protected int x;
    protected int y;
    protected Texture texture;

    public Entity(int x, int y, Texture texture){
        this.x = x;
        this.y = y;
        this.texture = texture;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public Texture getTexture(){
        return texture;
    }

    public abstract void render(Graphics g);
    public abstract void update();

    public void dispose(){
        texture.dispose();
    }

}

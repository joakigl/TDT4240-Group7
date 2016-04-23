package com.mygdx.game.Entities;

import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Andreas on 16.04.2016.
 */
public abstract class Entity {

    protected double x;
    protected double y;
    protected Texture texture;

    public Entity(int x, int y, Texture texture){
        this.x = x;
        this.y = y;
        this.texture = texture;
    }

    public void setPosition(float x, float y){
        this.x = (int)x;
        this.y = (int)y;
    }

    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return (int)x;
    }

    public int getY(){
        return (int)y;
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

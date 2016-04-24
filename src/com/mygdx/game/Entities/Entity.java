package com.mygdx.game.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Andreas on 16.04.2016.
 */
public abstract class Entity {

    protected float x;
    protected float y;
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
    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public abstract void render(SpriteBatch batch);
    public abstract void update();

    public void dispose(){
        texture.dispose();
    }

}

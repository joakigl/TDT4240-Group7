package com.mygdx.game.Entities;

import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Andreas on 16.04.2016.
 */
public class Pusher extends MovableEntity {

    private Vector2 prevPos;

    public Pusher(int x, int y, Texture texture) {
        super(x, y, texture);
        prevPos = new Vector2();
    }

    @Override
    public void render(Graphics g) {

    }

    @Override
    public void update(){

    }

    public void setPrevPos(float x, float y){
        prevPos.set(x,y);
    }

    public int getPrevX(){
        return (int)prevPos.x;
    }

    public int getPrevY(){
        return (int)prevPos.y;
    }
}

package com.mygdx.game.Entities;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Andreas on 16.04.2016.
 */
public abstract class MovableEntity extends Entity {

    protected double mx;
    protected double my;

    public MovableEntity(int x, int y, Texture texture) {
        super(x, y, texture);
    }

    public MovableEntity(int x, int y, Texture texture, double mx, double my){
        super(x, y, texture);
        this.mx = mx;
        this.my = my;
    }
}

package com.mygdx.game.Entities;

import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by Andreas on 16.04.2016.
 */
public abstract class MovableEntity extends Entity {

    protected double mx;
    protected double my;

    public MovableEntity(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }

    public MovableEntity(int x, int y, Sprite sprite, double mx, double my){
        super(x, y, sprite);
        this.mx = mx;
        this.my = my;
    }
}

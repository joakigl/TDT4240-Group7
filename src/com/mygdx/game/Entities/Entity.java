package com.mygdx.game.Entities;

import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by Andreas on 16.04.2016.
 */
public abstract class Entity {

    protected int x;
    protected int y;
    protected Sprite sprite;

    public Entity(int x, int y, Sprite sprite){
        this.x = x;
        this.y = y;
        this.sprite = sprite;
    }

    public abstract void render(Graphics g);
    public abstract void update();

}

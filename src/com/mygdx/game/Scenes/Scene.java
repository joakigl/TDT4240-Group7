package com.mygdx.game.Scenes;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Andreas on 16.04.2016.
 */
public abstract class Scene{
    public Scene(){

    }
    public abstract void render(SpriteBatch batch);
    public abstract void update();
}

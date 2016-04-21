package com.mygdx.game.Handlers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Scenes.Scene;

/**
 * Created by Andreas on 16.04.2016.
 */
public class SceneHandler {

    private Scene currentScene;

    public SceneHandler(Scene scene){
        currentScene = scene;
    }

    public void setScene(Scene scene){
        currentScene = scene;
    }

    public void render(SpriteBatch batch){
        currentScene.render(batch);
    }

    public void update() {
        currentScene.update();
    }
}

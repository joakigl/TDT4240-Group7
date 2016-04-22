package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Andreas on 17.04.2016.
 */
public class Res {

    /**
     * Class for global variables.
     * Make variables static
     */

    static public Texture menuBackground = new Texture(Gdx.files.internal("menu-backdrop.png"));
    static public Texture playButton = new Texture(Gdx.files.internal("play-button.png"));
    static public Texture optionsButton = new Texture(Gdx.files.internal("options-button.png"));
    static public Texture exitButton = new Texture(Gdx.files.internal("exit-button.png"));
    static public Texture gameplayBackground = new Texture(Gdx.files.internal("gameplay-backdrop.png"));
    static public Texture puck = new Texture(Gdx.files.internal("puck.png"));
    static public Texture pusherP1 = new Texture(Gdx.files.internal("push-p1.png"));
    static public Texture pusherP2 = new Texture(Gdx.files.internal("push-p2.png"));


}

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

    //Textures loaded on app opening
    static public Texture menuBackground = new Texture(Gdx.files.internal("menu-backdrop.png"));
    static public Texture playButton = new Texture(Gdx.files.internal("play-button.png"));
    static public Texture optionsButton = new Texture(Gdx.files.internal("options-button.png"));
    static public Texture exitButton = new Texture(Gdx.files.internal("exit-button.png"));
    static public Texture gameplayBackground = new Texture(Gdx.files.internal("gameplay-backdrop.png"));
    static public Texture puck = new Texture(Gdx.files.internal("puck.png"));
    static public Texture pusherP1 = new Texture(Gdx.files.internal("push-p1.png"));
    static public Texture pusherP2 = new Texture(Gdx.files.internal("push-p2.png"));
    static public Texture longsideWall = new Texture(Gdx.files.internal("side-wall.png"));
    static public Texture shortsideWall = new Texture(Gdx.files.internal("shortside-wall.png"));
    static public Texture noPointIndicator= new Texture(Gdx.files.internal("nopoint-indicator.png"));
    static public Texture coundownIndicator = new Texture(Gdx.files.internal("countdown-indicator.png"));
    static public Texture goIndicator = new Texture(Gdx.files.internal("go-indicator.png"));
    static public Texture p1PointIndicator = new Texture(Gdx.files.internal("redpoint-indicator.png"));
    static public Texture p2PointIndicator = new Texture(Gdx.files.internal("bluepoint-indicator.png"));

    //Scores
    static public int p1score = 0;
    static public int p2score = 0;
}

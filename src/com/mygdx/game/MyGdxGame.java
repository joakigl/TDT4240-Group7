package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Screens.MainMenu;

public class MyGdxGame extends Game {

    public OrthographicCamera camera;
    public SpriteBatch batch;

    public int gameWidth = 1280;
    public int gameHeight = 720;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
        this.setScreen(new MainMenu(this));
	}

	@Override
	public void render () {
		super.render();
	}

    @Override
    public void dispose(){
        batch.dispose();
    }
}

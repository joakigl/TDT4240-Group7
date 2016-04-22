package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.mygdx.game.Handlers.SceneHandler;
import com.mygdx.game.Handlers.TouchHandler;
import com.mygdx.game.Scenes.MainMenu;

public class MyGdxGame extends ApplicationAdapter {
    /**
     * Main class on core project.
     */

    //Rendering and scenes
    SpriteBatch batch;
    SceneHandler sceneHandler;

    //For tocuh
    TouchHandler touchHandler;
    public OrthographicCamera camera;
    ExtendViewport viewport;
    ShapeRenderer shapes;
    int width = 1280;
    int height = 720;
	
	@Override
	public void create () {
        camera = new OrthographicCamera();
        viewport = new ExtendViewport(720,1280);
		batch = new SpriteBatch();
        shapes = new ShapeRenderer();
        touchHandler = new TouchHandler(this);
        Gdx.input.setInputProcessor(touchHandler);
        sceneHandler = new SceneHandler(new MainMenu());
	}

	@Override
	public void render () {
        sceneHandler.update();
     	Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();
        sceneHandler.render(batch);
		batch.end();

        shapes.setProjectionMatrix(camera.combined);
        shapes.begin(ShapeRenderer.ShapeType.Filled);
        shapes.circle(touchHandler.point1.x, touchHandler.point1.y, 0.25f, 16);
        shapes.circle(touchHandler.point2.x, touchHandler.point2.y, 0.25f, 16);
        shapes.end();
	}
}

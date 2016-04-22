package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.Entities.Puck;
import com.mygdx.game.Entities.Pusher;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Res;

/**
 * Created by Andreas on 22.04.2016.
 */
public class GameScreen implements Screen {

    final MyGdxGame game;

    OrthographicCamera camera;
    private World world;
    private Box2DDebugRenderer b2dr;

    private Texture background;

    private Pusher p1;
    private Pusher p2;
    private Puck puck;

    public GameScreen(final MyGdxGame game){
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(true, 1280,720);
        Gdx.input.setCatchBackKey(true);

        background = Res.gameplayBackground;

        world = new World(new Vector2(0,0), false);
        b2dr = new Box2DDebugRenderer();

        p1 = new Pusher(200,game.gameHeight/2-(Res.pusherP2.getHeight()/2),Res.pusherP1);
        p2 = new Pusher(game.gameWidth-Res.pusherP2.getWidth()-200,game.gameHeight/2-(Res.pusherP2.getHeight()/2),Res.pusherP2);
        puck = new Puck(game.gameWidth/2-(Res.puck.getWidth()/2),game.gameHeight/2-(Res.puck.getHeight()/2),Res.puck);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(Gdx.gl20.GL_COLOR_BUFFER_BIT);
        camera.update();

        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        game.batch.draw(background, 0, 0);
        game.batch.draw(p1.getTexture(),p1.getX(),p1.getY());
        game.batch.draw(p2.getTexture(),p2.getX(),p2.getY());
        game.batch.draw(puck.getTexture(),puck.getX(),puck.getY());
        game.batch.end();

        for(int i = 0;i<2;i++) {
            if (Gdx.input.isTouched(i)) {
                Vector3 touchPos = new Vector3();
                touchPos.set(Gdx.input.getX(i), Gdx.input.getY(i), 0);
                camera.unproject(touchPos);
            }
        }

        if(Gdx.input.isKeyPressed(Input.Keys.BACK)){
            game.setScreen(new MainMenu(game));
            dispose();
        }

        world.step(1/60f, 6,2);

        b2dr.render(world,camera.combined);

    }

    @Override
    public void show() {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        world.dispose();
        b2dr.dispose();
    }
    
}

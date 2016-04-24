package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.Entities.Button;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Res;

/**
 * Created by Andreas on 22.04.2016.
 */
public class MainMenu implements Screen {

    final MyGdxGame game;

    OrthographicCamera camera;

    private Texture background;
    private Button playButton;
    private Button optionsButton;

    int buttonTouchDelay;

    public MainMenu(final MyGdxGame game){
        this.game = game;
        buttonTouchDelay = 20;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1280,720);

        background = Res.menuBackground;

        Gdx.input.setCatchBackKey(false);

        playButton = new Button((game.gameWidth/2)-Res.playButton.getWidth()/2,(game.gameHeight/2)-Res.playButton.getHeight()/2,Res.playButton);
        optionsButton = new Button(playButton.getX()+10+Res.optionsButton.getWidth(),(game.gameHeight/2)-Res.optionsButton.getHeight()/2,Res.optionsButton);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if(buttonTouchDelay>0){
            buttonTouchDelay--;
        }
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(Gdx.gl20.GL_COLOR_BUFFER_BIT);
        camera.update();

        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        game.batch.draw(background,0,0);
        game.batch.draw(playButton.getTexture(),playButton.getX(),playButton.getY());
        game.batch.draw(optionsButton.getTexture(),optionsButton.getX(),optionsButton.getY());
        game.batch.end();

        if(Gdx.input.isTouched() && buttonTouchDelay == 0){
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            if(playButton.pointOnButton(touchPos)){
                if(Res.soundsOn) {
                    Res.buttonPress.play(0.6f);
                }
                game.setScreen(new GameScreen(game));
                dispose();
            }else if(optionsButton.pointOnButton(touchPos)){
                if(Res.soundsOn) {
                    Res.buttonPress.play(0.6f);
                }
                game.setScreen(new OptionScreen(game));
                dispose();
            }
        }

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

    }
}

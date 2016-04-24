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
 * Created by Andreas on 23.04.2016.
 */
public class OptionScreen implements Screen {


    final MyGdxGame game;

    OrthographicCamera camera;

    private Texture background;
    private Button soundsButton;
    private Button mainmenuButton;

    int buttonTouchDelay = 20;

    public OptionScreen(final MyGdxGame game){
        this.game = game;
        buttonTouchDelay = 20;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1280, 720);

        background = Res.menuBackground;

        Gdx.input.setCatchBackKey(false);
        Texture temp;
        if(Res.soundsOn){
            temp = Res.soundisonButton;
        }else{
            temp = Res.soundisoffButton;
        }
        soundsButton = new Button((game.gameWidth/2)-temp.getWidth()/2,(game.gameHeight/2)-temp.getHeight()/2,temp);
        mainmenuButton = new Button(soundsButton.getX()+10+Res.mainmenuButton.getWidth(),(game.gameHeight/2)-Res.mainmenuButton.getHeight()/2,Res.mainmenuButton);
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
        game.batch.draw(soundsButton.getTexture(),soundsButton.getX(),soundsButton.getY());
        game.batch.draw(mainmenuButton.getTexture(),mainmenuButton.getX(),mainmenuButton.getY());
        game.batch.end();

        if(Gdx.input.isTouched() && buttonTouchDelay == 0){
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            if(soundsButton.pointOnButton(touchPos)){
                buttonTouchDelay = 20;
                if(!Res.soundsOn) {
                    Res.buttonPress.play(0.6f);
                    Res.soundsOn = true;
                    soundsButton.setTexture(Res.soundisonButton);
                }else{
                    soundsButton.setTexture(Res.soundisoffButton);
                    Res.soundsOn = false;
                }
            }else if(mainmenuButton.pointOnButton(touchPos)){
                if(Res.soundsOn){
                    Res.buttonPress.play(0.6f);
                }
                game.setScreen(new MainMenu(game));
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

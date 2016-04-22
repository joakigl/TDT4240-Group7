package com.mygdx.game.Handlers;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.MyGdxGame;

/**
 * Created by Andreas on 17.04.2016.
 */
public class TouchHandler implements InputProcessor {

    public Vector3 point1;
    public Vector3 point2;
    public boolean dragging1;
    public boolean dragging2;
    private MyGdxGame game;

    public TouchHandler(MyGdxGame game){
        this.game = game;
        point1 = new Vector3();
        point2 = new Vector3();
        dragging1 = false;
        dragging2 = false;
    }

    @Override public boolean touchDown (int screenX, int screenY, int pointer, int button) {
        // ignore if its not left mouse button or first touch pointer
        if(pointer == 0){
            game.camera.unproject(point1.set(screenX,screenY, 0));
            dragging1 = true;
            return true;
        }
        if(pointer == 1) {
            game.camera.unproject(point2.set(screenX, screenY, 0));
            dragging2 = true;
            return true;
        }
        return false;
    }

    @Override public boolean touchDragged (int screenX, int screenY, int pointer) {
        if(pointer == 0){
            game.camera.unproject(point1.set(screenX,screenY, 0));
            return true;
        }
        if(pointer == 1) {
            game.camera.unproject(point2.set(screenX, screenY, 0));
            return true;
        }
        return false;
    }

    @Override public boolean touchUp (int screenX, int screenY, int pointer, int button) {
        if(pointer == 0){
            game.camera.unproject(point1.set(screenX,screenY, 0));
            dragging1 = false;
            return false;
        }
        if(pointer == 1) {
            game.camera.unproject(point2.set(screenX, screenY, 0));
            dragging2 = false;
            return false;
        }
        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}

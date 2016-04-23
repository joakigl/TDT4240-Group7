package com.mygdx.game.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Andreas on 22.04.2016.
 */
public class Button extends Entity {

    private Rectangle boundingBox;

    public Button(int x, int y, Texture texture) {
        super(x, y, texture);
        boundingBox = new Rectangle(x,y,texture.getWidth(),texture.getHeight());
    }

    @Override
    public void render(SpriteBatch batch) {

    }

    @Override
    public void update() {
        boundingBox.x = (int)x;
        boundingBox.y = (int)y;
    }

    public boolean pointOnButton(Vector3 point1){
        if(boundingBox.contains(point1.x, point1.y)){
            return true;
        }
        return false;
    }
}

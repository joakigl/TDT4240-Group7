package com.mygdx.game.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.PowerUps.PowerUp;

/**
 * Created by Andreas on 16.04.2016.
 */
public class PowerUpEntity extends Entity{

    private PowerUp powerUp;

    public PowerUpEntity(int x, int y, Texture texture) {
        super(x, y, texture);
    }

    @Override
    public void render(SpriteBatch batch) {

    }

    @Override
    public void update() {

    }
}

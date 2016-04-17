package com.mygdx.game.Entities;

import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.PowerUps.PowerUp;

/**
 * Created by Andreas on 16.04.2016.
 */
public class PowerUpEntity extends Entity{

    private PowerUp powerUp;

    public PowerUpEntity(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }

    @Override
    public void render(Graphics g) {

    }

    @Override
    public void update() {

    }
}

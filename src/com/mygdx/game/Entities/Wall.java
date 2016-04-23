package com.mygdx.game.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.MyGdxGame;

/**
 * Created by Andreas on 23.04.2016.
 */
public class Wall extends Entity {

    private MyGdxGame game;

    public Wall(MyGdxGame game, int x, int y, Texture texture) {
        super(x, y, texture);
        this.game = game;
    }

    public Body pBody;
    public void createBox2DBody(World world){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set((x + texture.getWidth()/2) / game.PPM,(y+texture.getHeight()/2)/game.PPM);
        pBody = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(texture.getWidth()/2/game.PPM, texture.getHeight()/2/game.PPM); //divide by 2 if wonky?

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0.1f; //Don't know what density or restitution we should use
        fixtureDef.restitution = 0.5f;

        pBody.createFixture(fixtureDef);
        shape.dispose();
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(texture,(pBody.getPosition().x*game.PPM)-texture.getWidth()/2,(pBody.getPosition().y*game.PPM)-texture.getHeight()/2);
    }

    @Override
    public void update() {

    }
}

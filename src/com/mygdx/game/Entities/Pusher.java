package com.mygdx.game.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.MyGdxGame;

/**
 * Created by Andreas on 16.04.2016.
 */
public class Pusher extends MovableEntity {

    private Vector2 prevPos;

    private MyGdxGame game;

    public Pusher(MyGdxGame game, int x, int y, Texture texture) {
        super(x, y, texture);
        this.game = game;
        prevPos = new Vector2();
    }

    public Body pBody;
    public void createBox2DBody(World world){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set((x + texture.getWidth()/2) / game.PPM,(y+texture.getHeight()/2)/game.PPM);
        pBody = world.createBody(bodyDef);

        CircleShape shape = new CircleShape();
        shape.setRadius(64/game.PPM);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0.1f; //Don't know what density or restitution we should use
        fixtureDef.restitution = 0.5f;

        pBody.createFixture(fixtureDef);
        shape.dispose();

    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(texture,x,y);
    }

    @Override
    public void update(){

    }

    @Override
    public void dispose(){
    }

    public void setPrevPos(float x, float y){
        prevPos.set(x,y);
    }

    public int getPrevX(){
        return (int)prevPos.x;
    }

    public int getPrevY(){
        return (int)prevPos.y;
    }
}

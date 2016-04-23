package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
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

    private boolean point1drag = false;
    private boolean point2drag = false;
    private int point1target = 0; //0 is no target
    private int point2target = 0; //0 is no target

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
        puck.setVel(0.1,0);
        Res.p1score = 1;
        Res.p2score = 2;
    }

    @Override
    public void render(float delta) {
        if(Gdx.input.isKeyPressed(Input.Keys.BACK)){
            game.setScreen(new MainMenu(game));
            dispose();
        }

        for(int i = 0;i<2;i++) {
            if(!Gdx.input.isTouched(i)) continue;
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(i), Gdx.input.getY(i), 0);
            camera.unproject(touchPos);
            if(touchPos.x < game.gameWidth/2){
                p1.setPrevPos(p1.getX(),p1.getY());
                p1.setPosition(touchPos.x-(p1.getTexture().getWidth()/2),touchPos.y-(p1.getTexture().getHeight()/2));
            }
            if(touchPos.x > game.gameWidth/2){
                p2.setPrevPos(p2.getX(),p2.getY());
                p2.setPosition(touchPos.x-(p2.getTexture().getWidth()/2),touchPos.y-(p2.getTexture().getHeight()/2));
            }
        }

        puck.update();

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(Gdx.gl20.GL_COLOR_BUFFER_BIT);
        camera.update();

        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        game.batch.draw(background, 0, 0);
        drawIndicators();
        drawWalls();
        game.batch.draw(p1.getTexture(),p1.getX(),p1.getY());
        game.batch.draw(p2.getTexture(),p2.getX(),p2.getY());
        game.batch.draw(puck.getTexture(),puck.getX(),puck.getY());
        game.batch.end();

        world.step(1/60f, 6,2);

        b2dr.render(world,camera.combined);

    }

    public void drawWalls(){
        game.batch.draw(Res.longsideWall, 20, 0);
        game.batch.draw(Res.longsideWall, 20, game.gameHeight-40);
        game.batch.draw(Res.shortsideWall, 0,0);
        game.batch.draw(Res.shortsideWall, 0,game.gameHeight-Res.shortsideWall.getHeight());
        game.batch.draw(Res.shortsideWall, game.gameWidth-20,game.gameHeight-Res.shortsideWall.getHeight());
        game.batch.draw(Res.shortsideWall, game.gameWidth-20,0);
    }

    public void drawIndicators(){
        //draw left
        for(int i = 0;i<3;i++){
            if(i<Res.p1score){
                game.batch.draw(Res.p1PointIndicator, game.gameWidth/2-(Res.p1PointIndicator.getWidth()/2),60+(i*100));
            }else{
                game.batch.draw(Res.noPointIndicator, game.gameWidth/2-(Res.noPointIndicator.getWidth()/2),60+(i*100));
            }
        }

        for(int i = 0;i<3;i++){
            if(i<Res.p2score){
                game.batch.draw(Res.p2PointIndicator, game.gameWidth/2-(Res.p2PointIndicator.getWidth()/2),game.gameHeight-140-(i*100));
            }else{
                game.batch.draw(Res.noPointIndicator, game.gameWidth/2-(Res.noPointIndicator.getWidth()/2),game.gameHeight-140-(i*100));
            }
        }
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

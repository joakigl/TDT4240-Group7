package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.Entities.Button;
import com.mygdx.game.Entities.Puck;
import com.mygdx.game.Entities.Pusher;
import com.mygdx.game.Entities.Wall;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Res;

/**
 * Created by Andreas on 22.04.2016.
 */
public class GameScreen implements Screen {

    final MyGdxGame game;

    //0=countdown,1=gameplay,2=winner,3=pause
    private int gameState = 0;

    OrthographicCamera camera;
    private World world;
    private Box2DDebugRenderer b2dr;

    private Texture background;

    private Pusher p1, p2;
    private Puck puck;
    private Wall topWall,botWall,leftTopWall,leftBotWall,rightTopWall,rightBotWall;

    private Button resumeButton;
    private Button mainmenuButton;

    int buttonTouchDelay;
    //misc, for countdown, winnerscreen, etc
    int time;
    int countdown;

    public GameScreen(final MyGdxGame game){
        this.game = game;
        buttonTouchDelay = 20;

        //gamesize and misc
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1280,720);
        Gdx.input.setCatchBackKey(true);

        //gameplay background
        background = Res.gameplayBackground;

        //init box2d world
        world = new World(new Vector2(0,0), false);
        b2dr = new Box2DDebugRenderer();

        //Create objects
        p1 = new Pusher(game, 200,game.gameHeight/2-(Res.pusherP2.getHeight()/2),Res.pusherP1);
        p1.createBox2DBody(world);
        p2 = new Pusher(game, game.gameWidth-Res.pusherP2.getWidth()-200,game.gameHeight/2-(Res.pusherP2.getHeight()/2),Res.pusherP2);
        p2.createBox2DBody(world);
        puck = new Puck(game, game.gameWidth/2-(Res.puck.getWidth()/2),game.gameHeight/2-(Res.puck.getHeight()/2),Res.puck);
        puck.createBox2DBody(world);
        int direction = (int)Math.random();
        if(direction == 0) {
            puck.pBody.setLinearVelocity(-0.8f, 0);
        }else{
            puck.pBody.setLinearVelocity(0.8f,0);
        }
        topWall = new Wall(game,20,game.gameHeight-Res.longsideWall.getHeight(),Res.longsideWall);
        topWall.createBox2DBody(world);
        botWall = new Wall(game,20,0,Res.longsideWall);
        botWall.createBox2DBody(world);
        leftTopWall = new Wall(game,0,game.gameHeight-Res.shortsideWall.getHeight(),Res.shortsideWall);
        leftTopWall.createBox2DBody(world);
        leftBotWall = new Wall(game,0,0,Res.shortsideWall);
        leftBotWall.createBox2DBody(world);
        rightTopWall = new Wall(game,game.gameWidth-Res.shortsideWall.getWidth(),game.gameHeight-Res.shortsideWall.getHeight(),Res.shortsideWall);
        rightTopWall.createBox2DBody(world);
        rightBotWall = new Wall(game,game.gameWidth-Res.shortsideWall.getWidth(),0,Res.shortsideWall);
        rightBotWall.createBox2DBody(world);

        //init buttons for pause
        resumeButton = new Button((game.gameWidth/2)-Res.resumeButton.getWidth()/2,(game.gameHeight/2)-Res.resumeButton.getHeight()/2,Res.resumeButton);
        mainmenuButton = new Button(resumeButton.getX()+10+Res.mainmenuButton.getWidth(),(game.gameHeight/2)-Res.mainmenuButton.getHeight()/2,Res.mainmenuButton);

        //init scores
        Res.p1score = 0;
        Res.p2score = 0;

        //countdown variables for countdown phase before playing
        time = 60;
        countdown = 3;
    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(Gdx.gl20.GL_COLOR_BUFFER_BIT);
        camera.update();

        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();

        game.batch.draw(background, 0, 0);
        drawWalls();
        switch(gameState){
            case 0: //countdown
                drawCountdownIndicators();
                break;
            case 1: //gameplay
                drawGameplayIndicators();
                p1.render(game.batch);
                p2.render(game.batch);
                puck.render(game.batch);
                break;
            case 2: //winner
                drawWinnerIndicators();
                break;
            case 3: //pause
                game.batch.draw(Res.pausedBackground,0,0);
                game.batch.draw(resumeButton.getTexture(),resumeButton.getX(),resumeButton.getY());
                game.batch.draw(mainmenuButton.getTexture(),mainmenuButton.getX(),mainmenuButton.getY());
                break;
        }
        game.batch.end();

    }

    public void update(float delta){
        switch(gameState){
            case 0: //countdown
                time--;
                if(time==0){
                    countdown--;
                    if(countdown>=0){
                        if(Res.soundsOn){
                            if(countdown>0){
                                Res.coundownBeep.play(0.6f);
                            }else if(countdown==0){
                                Res.gameStart.play(0.6f);
                            }
                        }
                        time=60;
                    }else{
                        gameState = 1;
                        time=60;
                        countdown = 3;
                    }
                }
                break;
            case 1: //gameplay

                if(Gdx.input.isKeyPressed(Input.Keys.BACK)){
                    gameState = 3;
                }

                for(int i = 0;i<2;i++) {
                    if(!Gdx.input.isTouched(i)) continue;
                    Vector3 touchPos = new Vector3();
                    touchPos.set(Gdx.input.getX(i), Gdx.input.getY(i), 0);
                    camera.unproject(touchPos);
                    Vector2 direction = new Vector2(touchPos.x, touchPos.y);
                    // calculte the normalized direction from the body to the touch position
                    if(touchPos.x < (game.gameWidth/2)-50){

                        Vector2 bodyPos = new Vector2(p1.pBody.getPosition().x*game.PPM,p1.pBody.getPosition().y*game.PPM);
                        direction.sub(bodyPos);
                        float speed = direction.len();
                        direction.nor();
                        if(speed >=50) {
                            p1.pBody.setLinearVelocity(direction.scl(50));
                        }else if(speed<50){
                            p1.pBody.setLinearVelocity(direction.scl(speed * 0.8f));
                        }
                    }
                    if(touchPos.x > (game.gameWidth/2)+50){
                        Vector2 bodyPos=new Vector2(p2.pBody.getPosition().x*game.PPM,p2.pBody.getPosition().y*game.PPM);
                        direction.sub(bodyPos);
                        float speed = direction.len();
                        direction.nor();
                        if(speed >= 50) {
                            p2.pBody.setLinearVelocity(direction.scl(50));
                        }else if(speed<50){
                            p2.pBody.setLinearVelocity(direction.scl(speed * 0.8f));
                        }
                    }
                }
                if(puck.pBody.getPosition().x*game.PPM < -10){
                    Res.p2score+=1;
                    world.destroyBody(puck.pBody);
                    puck = new Puck(game, game.gameWidth/2-(Res.puck.getWidth()/2),game.gameHeight/2-(Res.puck.getHeight()/2),Res.puck);
                    puck.createBox2DBody(world);
                    puck.pBody.setLinearVelocity(-0.8f, 0);
                    if(Res.soundsOn){
                        Res.scoreSound.play(0.6f);
                    }
                }else if(puck.pBody.getPosition().x*game.PPM > game.gameWidth+10){
                    Res.p1score+=1;
                    world.destroyBody(puck.pBody);
                    puck = new Puck(game, game.gameWidth/2-(Res.puck.getWidth()/2),game.gameHeight/2-(Res.puck.getHeight()/2),Res.puck);
                    puck.createBox2DBody(world);
                    puck.pBody.setLinearVelocity(0.8f,0);
                    if(Res.soundsOn){
                        Res.scoreSound.play(0.6f);
                    }
                }
                if(Res.p1score == 3 || Res.p2score == 3){
                    gameState = 2;
                }

                world.setContactListener(
                        new ContactListener() {
                            @Override
                            public void beginContact(Contact contact) {
                                if(contact.getFixtureA().getBody() == puck.pBody || contact.getFixtureB().getBody() == puck.pBody){
                                    //put audio here for puck collision sounds
                                    if(Res.soundsOn){
                                        Res.puckHit.play(0.6f);
                                    }
                                }
                            }

                            @Override
                            public void endContact(Contact contact) {

                            }

                            @Override
                            public void preSolve(Contact contact, Manifold oldManifold) {

                            }

                            @Override
                            public void postSolve(Contact contact, ContactImpulse impulse) {

                            }
                        }
                );

                world.step(1/60f, 6,2);

                p1.pBody.setLinearVelocity(new Vector2(0,0));
                p2.pBody.setLinearVelocity(new Vector2(0,0));
                break;
            case 2: //winner
                time--;
                if(time==0){
                    time = 60;
                    if(countdown >1){
                        countdown--;
                    }
                }
                if(countdown<=1){
                    if(Gdx.input.isTouched()){
                        game.setScreen(new MainMenu(game));
                        dispose();
                    }
                }
                break;
            case 3: //pause
                if(buttonTouchDelay>0){
                    buttonTouchDelay--;
                }
                if(Gdx.input.isTouched()){
                    Vector3 touchPos = new Vector3();
                    touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
                    camera.unproject(touchPos);
                    if(resumeButton.pointOnButton(touchPos)){
                        gameState = 1;
                        buttonTouchDelay=20;
                    }else if(mainmenuButton.pointOnButton(touchPos)){
                        game.setScreen(new MainMenu(game));
                        dispose();
                    }
                }
                break;
        }
    }

    public void drawWalls(){
        topWall.render(game.batch);
        botWall.render(game.batch);
        leftTopWall.render(game.batch);
        leftBotWall.render(game.batch);
        rightTopWall.render(game.batch);
        rightBotWall.render(game.batch);
    }

    public void drawWinnerIndicators(){
        for(int i = 0;i<3;i++){
            if(time>30) {
                if (Res.p1score==3) {
                    game.batch.draw(Res.p1PointIndicator, game.gameWidth / 2 - (Res.p1PointIndicator.getWidth() / 2), 60 + (i * 100));
                }else if (Res.p2score==3) {
                    game.batch.draw(Res.p2PointIndicator, game.gameWidth / 2 - (Res.p2PointIndicator.getWidth() / 2), 60 + (i * 100));
                }
            }else {
                game.batch.draw(Res.noPointIndicator, game.gameWidth / 2 - (Res.noPointIndicator.getWidth() / 2), 60 + (i * 100));
            }
        }

        for(int i = 0;i<3;i++){
            if(time>30) {
                if (Res.p1score==3) {
                    game.batch.draw(Res.p1PointIndicator, game.gameWidth / 2 - (Res.p1PointIndicator.getWidth() / 2), game.gameHeight - 140 - (i * 100));
                } else if(Res.p2score==3){
                    game.batch.draw(Res.p2PointIndicator, game.gameWidth / 2 - (Res.p2PointIndicator.getWidth() / 2), game.gameHeight - 140 - (i * 100));
                }
            }else{
                game.batch.draw(Res.noPointIndicator, game.gameWidth/2-(Res.noPointIndicator.getWidth()/2),game.gameHeight-140-(i*100));
            }
        }
    }

    public void drawGameplayIndicators(){
        //draw left
        for(int i = 0;i<3;i++){
            if(i<Res.p1score){
                game.batch.draw(Res.p1PointIndicator, game.gameWidth / 2 - (Res.p1PointIndicator.getWidth() / 2), 60 + (i * 100));
            }else{
                game.batch.draw(Res.noPointIndicator, game.gameWidth / 2 - (Res.noPointIndicator.getWidth() / 2), 60 + (i * 100));
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

    public void drawCountdownIndicators(){
        if(countdown>0) {
            for (int i = 0; i < 3; i++) {
                if (time > 30) {
                    game.batch.draw(Res.countdownIndicator, game.gameWidth / 2 - (Res.countdownIndicator.getWidth() / 2), 60 + (i * 100));
                } else {
                    game.batch.draw(Res.noPointIndicator, game.gameWidth / 2 - (Res.noPointIndicator.getWidth() / 2), 60 + (i * 100));
                }
            }

            for (int i = 0; i < 3; i++) {
                if (time > 30) {
                    game.batch.draw(Res.countdownIndicator, game.gameWidth / 2 - (Res.countdownIndicator.getWidth() / 2), game.gameHeight - 140 - (i * 100));
                } else {
                    game.batch.draw(Res.noPointIndicator, game.gameWidth / 2 - (Res.noPointIndicator.getWidth() / 2), game.gameHeight - 140 - (i * 100));
                }
            }
        }else if(countdown==0){
            for (int i = 0; i < 3; i++) {
                game.batch.draw(Res.goIndicator, game.gameWidth / 2 - (Res.goIndicator.getWidth() / 2), 60 + (i * 100));
            }

            for (int i = 0; i < 3; i++) {
                game.batch.draw(Res.goIndicator, game.gameWidth / 2 - (Res.goIndicator.getWidth() / 2), game.gameHeight - 140 - (i * 100));
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

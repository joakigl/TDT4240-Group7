package com.mygdx.game;

        import com.badlogic.gdx.Game;
        import com.badlogic.gdx.graphics.OrthographicCamera;
        import com.badlogic.gdx.graphics.g2d.SpriteBatch;
        import com.mygdx.game.Screens.MainMenu;

public class MyGdxGame extends Game {

    public boolean debug;

    public OrthographicCamera camera;
    public SpriteBatch batch;

    public int gameWidth = 1280;
    public int gameHeight = 720;

    public float PPM = 128f;

    @Override
    public void create () {
        batch = new SpriteBatch();
        this.setScreen(new MainMenu(this));
    }

    @Override
    public void render () {
        super.render();
    }

    @Override
    public void dispose(){
        batch.dispose();
        Res.menuBackground.dispose();
        Res.playButton.dispose();
        Res.optionsButton.dispose();
        Res.exitButton.dispose();
        Res.resumeButton.dispose();
        Res.mainmenuButton.dispose();
        Res.soundisonButton.dispose();
        Res.soundisoffButton.dispose();
        Res.gameplayBackground.dispose();
        Res.pausedBackground.dispose();
        Res.puck.dispose();
        Res.pusherP1.dispose();
        Res.pusherP2.dispose();
        Res.longsideWall.dispose();
        Res.shortsideWall.dispose();
        Res.noPointIndicator.dispose();
        Res.countdownIndicator.dispose();
        Res.goIndicator.dispose();
        Res.p1PointIndicator.dispose();
        Res.p2PointIndicator.dispose();
        Res.puckHit.dispose();
        Res.coundownBeep.dispose();
        Res.gameStart.dispose();
        Res.scoreSound.dispose();
        Res.buttonPress.dispose();
    }
}

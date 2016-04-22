package com.mygdx.game.assets;

/**
 * Created by Andreas on 19.04.2016.
 */
public class Point2D {

    private int x;
    private int y;

    public Point2D(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Point2D(){
        this.x = 0;
        this.y = 0;
    }

    public void setPoint(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

}

package com.company;

import java.awt.*;

/**
 * Created by ranxiao on 25/2/17.
 */
public class FighterJet extends GameObject implements SceneObject {


    private GameScene scene;
    private int speed, dx, dy;
    public FighterJet(GameScene scene, int x, int y, int speed) {
        super(x, y, 16, 40, 10, 30);
        scene.add(this);
        this.scene = scene;
        this.speed = speed;
        dx = 0; dy = 0;
    }

    @Override
    public void move() {
        Position center = getCenter();
        int cx = center.x;
        int cy = center.y;
        int nx = cx+dx;
        int ny = cy+dy;
        if(pointInScene(nx, ny)){
           setCenter(nx, ny);
        }
    }

    public void stop(){
        dx=0;
        dy=0;
    }

    public void moveForward(){
        dx=0;
        dy=-speed;

    }

    public void moveBack(){
        dx=0;
        dy=speed;
    }

    public void moveLeft(){
        dx=-speed;
        dy=0;
    }

    public void moveRight(){
        dx=speed;
        dy=0;
    }

    @Override
    public boolean isInScene() {
        Position center = getCenter();
       return pointInScene(center.x, center.y);
    }

    private boolean pointInScene(int x, int y){
        int scene_width = scene.getWidth();
        int scene_height = scene.getHeight();
        return x>=0 && x<= scene_width && y>=0 && y <= scene_height;
    }

    @Override
    public void draw(Graphics g) {
        Position p0 = new Position(getCenter().x, y);
        Position p1 = new Position(x, y+height);
        Position p2 = new Position(x+width, y+height);
        Polygon polygon = new Polygon(new int[]{p0.x, p1.x, p2.x},
                                    new int[]{p0.y, p1.y, p2.y}, 3);
        g.setColor(Color.blue);
        g.fillRect(x, y+30, width, height-30);
        g.setColor(Color.blue);
        g.fillPolygon(polygon);
    }

}

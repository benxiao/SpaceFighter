package com.company;

import java.awt.*;

/**
 * Created by ranxiao on 25/2/17.
 */
public class Bullet extends GameObject implements SceneObject {
    private int speed;
    private GameScene scene;
    public Bullet(GameScene scene, int x, int y, int speed){
        super(x, y,4, 8,1, 1);
        this.speed=speed;
        this.scene = scene;
        scene.add(this);
    }

    public boolean isInScene(){
        Position center = getCenter();
        int sceneWidth = scene.getWidth();
        int sceneHeight = scene.getHeight();
        return (center.x>=0) && (center.x<= sceneWidth) && (center.y>=0) && (center.y<=sceneHeight);
    }

    public void draw(Graphics g){
        g.setColor(Color.GREEN);
        g.fillRect(x, y, width, height);
    }

    public void move(){
        y -= speed;
    }

}

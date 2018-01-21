package com.company;

import java.awt.*;

/**
 * Created by ranxiao on 25/2/17.
 */
public class BadGuy extends GameObject implements SceneObject {
    private int speed;
    private GameScene scene;
    private int counter=0;
    public BadGuy(GameScene scene, int x, int y){
        super(x, y,20,20,5, 4);
        this.speed=1;
        this.scene = scene;
        scene.add(this);
    }

    public boolean isInScene(){
        Position center = getCenter();
        int scene_width = scene.getWidth();
        int scene_height = scene.getHeight();
        return center.x>=0 && center.x<= scene_width && center.y>=0 && center.y <=scene_height;
    }

    public void draw(Graphics g){
        g.setColor(Color.RED);
        g.fillOval(x,y,width,height);
        g.drawRect(x,y,width,height);

    }

    public void move(){
        counter = (++counter) % 3;
        if (counter==0){
            y+=speed;
        }
    }
}

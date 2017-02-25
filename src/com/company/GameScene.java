package com.company;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by ranxiao on 25/2/17.
 */
public class GameScene {

    private List<SceneObject> elements;
    private int width, height;
    private Position[]whereBadGuysAppear;
    private Random rng;



    public GameScene(int width, int height) {
        elements = new ArrayList<>();
        this.width = width;
        this.height = height;
        whereBadGuysAppear = new Position[]{new Position(40, 40),
                                            new Position(80, 40),
                                            new Position(120, 40),
                                            new Position(160, 40),
                                            new Position(200, 40),
                                            new Position(240, 40),
                                            new Position(280, 40)
        };
        rng = new Random();
    }
    void add(SceneObject e){
        elements.add(e);
    }


    public int getWidth() {return width;}
    public int getHeight() {return height;}

    public void moveObjects(){
        for(SceneObject o: elements){
            if (o.isInScene() && o.isAlive()){
                o.move();
            }
            if ((o instanceof BadGuy) && (!o.isAlive() ||!o.isInScene())){
                Position p = whereBadGuysAppear[rng.nextInt(4)];
                ((BadGuy) o).reanimate(p.x ,p.y);
            }
        }
        for(SceneObject a: elements){
            for(SceneObject b: elements){
                if (a != b && a.isAlive() && b.isAlive()){
                    GameObject a1 = (GameObject) a;
                    GameObject b1 = (GameObject) b;
                    if (a1.collideWith(b1)){
                        a1.getHitBy(b1);

                    }
                }
            }
        }
    }

    public void drawObjects(Graphics g){
        for(SceneObject o: elements){
            if(o.isInScene() && o.isAlive()){
                o.draw(g);
            }
        }
    }
}


package com.company;

import java.awt.*;

/**
 * Created by ranxiao on 25/2/17.
 */
public interface SceneObject {
    void move();
    boolean isInScene();
    boolean isAlive();
    void draw(Graphics g);

}

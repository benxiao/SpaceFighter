package com.company;

import java.awt.*;

/**
 * Created by ranxiao on 24/2/17.
 */
public class GameObject extends Rectangle {
    private int damage, hitPoints, fullHitPoints;
    public GameObject(int x, int y, int width, int height, int damage, int hitPoints){
        super(x, y, width, height);
        this.damage = damage;
        this.fullHitPoints = hitPoints;
        this.hitPoints = hitPoints;
        setCenter(x,y);
    }
    public boolean isAlive(){return hitPoints > 0;}

    int getDamage(){ return damage; }

    public void getHitBy(GameObject o){
        if (this.intersects(o) && o.isAlive() && this.isAlive()){
            this.hitPoints -= o.damage;
            o.hitPoints -= this.damage;
        }
    }

    public Position getCenter(){
        return new Position(x+width/2, y+height/2);
    }

    public void setCenter(int x, int y){
        this.x = x-width/2;
        this.y = y-height/2;
    }

    public void reanimate(int x, int y){
        hitPoints = fullHitPoints;
        setCenter(x, y);
    }

    public boolean collideWith(GameObject o){
        return this.intersects(o);
    }

}

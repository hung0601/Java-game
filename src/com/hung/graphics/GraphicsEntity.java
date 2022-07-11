/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hung.graphics;

import com.hung.effect.Animation;
import com.hung.gameobjects.GameObject;
import com.hung.state.GameWorld;
import com.hung.gameobjects.ParticularObject;
import java.awt.Graphics2D;

/**
 *
 * @author manhh
 */
public abstract class GraphicsEntity extends GameObject{
    
    private ParticularObject object;
    public Animation runLeft, runRight, runUp, runDown;
    public Animation idleLeft, idleRight, idleUp, idleDown;
    public Animation attackLeft, attackRight, attackUp, attackDown;
    public Animation  behurtUp,behurtDown,behurtLeft,behurtRight;
    public Animation deadLeft, deadRight, deadUp, deadDown;
    
    public GraphicsEntity(float x,float y,GameWorld gameWorld){
        super(x,y,gameWorld);
    }
    
    @Override
    public float getPosX(){
        return object.getPosX();
    }
    @Override
    public float getPosY(){
        return object.getPosY();
    }
    
    public void setObject(ParticularObject object){
        this.object=object;
    }
    public ParticularObject getObject(){
        return object;
    }
    
    public void wait5Seconds(){
        long beginTime= System.nanoTime();
        while((System.nanoTime()-beginTime)/1000<5000000);
    }
    public abstract void draw(Graphics2D g2);
    
    
}

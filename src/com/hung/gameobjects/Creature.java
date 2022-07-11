/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hung.gameobjects;

import com.hung.state.GameWorld;
import java.awt.Rectangle;

/**
 *
 * @author manhh
 */
public abstract class Creature extends ParticularObject{

    public boolean isAttacking = false;
    public long lastAttackTime;
    public Creature(float x, float y, float width, float height, int blood, GameWorld gameWorld) {
        super(x, y, width, height, blood, gameWorld);
        setState(ALIVE);
    }

    public abstract void run();
    public abstract void stopRun();

    
    @Override
    public void Update(){
        
        super.Update();
        
        if(getState() == ALIVE || getState() == BEHURT ){
                setPosX(getPosX() + getSpeedX());


                if(getSpeedX()<0 && 
                        getGameWorld().physicalMap.haveCollisionWithLeftWall(getBoundForCollisionWithMap())!=null){

                    Rectangle rectLeftWall = getGameWorld().physicalMap.haveCollisionWithLeftWall(getBoundForCollisionWithMap());
                    setPosX(rectLeftWall.x + rectLeftWall.width + getWidth()/2);

                }
                if(getSpeedX()>0  && 
                        getGameWorld().physicalMap.haveCollisionWithRightWall(getBoundForCollisionWithMap())!=null){

                    Rectangle rectRightWall = getGameWorld().physicalMap.haveCollisionWithRightWall(getBoundForCollisionWithMap());
                    setPosX(rectRightWall.x - getWidth()/2);

                }
                
                
                Rectangle boundForCollisionWithMapFuture = getBoundForCollisionWithMap();
                boundForCollisionWithMapFuture.y += (getSpeedY()!=0?getSpeedY(): 2);
                Rectangle rectLand = getGameWorld().physicalMap.haveCollisionWithLand(boundForCollisionWithMapFuture);
                
                Rectangle rectTop = getGameWorld().physicalMap.haveCollisionWithTop(boundForCollisionWithMapFuture);
                
                if(rectTop !=null){
                    setPosY(rectTop.y + getGameWorld().physicalMap.getTileSize() - getHeight()/2+5);
                    
                }else if(rectLand != null){
                    setPosY(rectLand.y - getHeight()/2 - 1);
                }else{
                    setPosY(getPosY() + getSpeedY());
                }



               
                
            
        }
    }
    
}

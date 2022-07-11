/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hung.gameobjects;

import com.hung.state.GameWorld;
import com.hung.effect.CacheDataLoader;
import com.hung.userinterface.GameFrame;

/**
 *
 * @author manhh
 */
public class Camera extends GameObject {

    private float widthView;
    private float heightView;
    
    private boolean isLocked = false;
    
    public Camera(float x, float y, float widthView, float heightView, GameWorld gameWorld) {
        super(x, y, gameWorld);
        this.widthView = widthView;
        this.heightView = heightView;
    }

    public void lock(){
        isLocked = true;
    }
    
    public void unlock(){
        isLocked = false;
    }
    
    @Override
    public void Update() {
    
        // NOTE: WHEN SEE FINAL BOSS, THE CAMERA WON'T CHANGE THE POSITION,
        // AFTER THE TUTORIAL, CAMERA WILL SET THE NEW POS
        
        if(!isLocked){
        
            Knight mainCharacter = (Knight)getGameWorld().knightEntity.getKnight();

            if(mainCharacter.getPosX() - getPosX() > 400 
                    && CacheDataLoader.getInstance().getBackGroundMap(getGameWorld().maptype).getImagewidth()-mainCharacter.getPosX() + 400>GameFrame.SCREEN_WIDTH) 
                setPosX(mainCharacter.getPosX() - 400);
            if(mainCharacter.getPosX() - getPosX() < 200 && mainCharacter.getPosX()>200 ) setPosX(mainCharacter.getPosX() - 200);

            if(mainCharacter.getPosY() - getPosY() > 400
                    && CacheDataLoader.getInstance().getBackGroundMap(getGameWorld().maptype).getImageHeight()-mainCharacter.getPosY() + 400>GameFrame.SCREEN_HEIGHT) 
                setPosY(mainCharacter.getPosY() - 400); // bottom
            else if(mainCharacter.getPosY() - getPosY() < 250 && mainCharacter.getPosY()>250) setPosY(mainCharacter.getPosY() - 250);// top 
        }
    
    }

    public float getWidthView() {
        return widthView;
    }

    public void setWidthView(float widthView) {
        this.widthView = widthView;
    }

    public float getHeightView() {
        return heightView;
    }

    public void setHeightView(float heightView) {
        this.heightView = heightView;
    }
    
}


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hung.gameobjects;

import com.hung.state.GameWorld;
import java.awt.Graphics2D;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;



/**
 *
 * @author manhh
 */
public class ParticularObjectManager {
    protected List<ParticularObject> particularObjects;

    private GameWorld gameWorld;
    
    public ParticularObjectManager(GameWorld gameWorld){
        
        particularObjects = Collections.synchronizedList(new LinkedList<ParticularObject>());
        this.gameWorld = gameWorld;
        
    }
    
    public GameWorld getGameWorld(){
        return gameWorld;
    }
    
    public void addObject(ParticularObject particularObject){
        
        
        synchronized(particularObjects){
            particularObjects.add(particularObject);
        }
        
    }
    
    public void RemoveObject(ParticularObject particularObject){
        synchronized(particularObjects){
        
            for(int id = 0; id < particularObjects.size(); id++){
                
                ParticularObject object = particularObjects.get(id);
                if(object == particularObject)
                    particularObjects.remove(id);

            }
        }
    }
    
    public ParticularObject getCollisionWidthEnemyObject(ParticularObject object){
        synchronized(particularObjects){
            for(int id = 0; id < particularObjects.size(); id++){
                
                ParticularObject objectInList = particularObjects.get(id);

                if(object.getTeamType() != objectInList.getTeamType() && 
                        object.getBoundForCollisionWithEnemy().intersects(objectInList.getBoundForCollisionWithEnemy())){
                    return objectInList;
                }
            }
        }
        return null;
    }
    
    public void UpdateObjects(){
        
        synchronized(particularObjects){
            for(int id = 0; id < particularObjects.size(); id++){
                
                ParticularObject object = particularObjects.get(id);
                
                
                if(!object.isObjectOutOfCameraView()) object.Update();
                
                if(object.getState() == ParticularObject.DEATH){
                    particularObjects.remove(id);
                    gameWorld.knightEntity.getKnight().setCoin(gameWorld.knightEntity.getKnight().getCoin() + 10);
                    gameWorld.sMessage.addMessage("+10 Coint");
                }
            }
        }

        //System.out.println("Camerawidth  = "+camera.getWidth());
        
    }
    
   
}

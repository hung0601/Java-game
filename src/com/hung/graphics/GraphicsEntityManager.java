/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hung.graphics;

import com.hung.effect.Animation;
import com.hung.state.GameWorld;
import com.hung.gameobjects.ParticularObject;
import java.awt.Graphics2D;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author manhh
 */
public class GraphicsEntityManager{
    protected List<GraphicsEntity> graphicsEntities;

    private GameWorld gameWorld;
    
    public GraphicsEntityManager(GameWorld gameWorld){
        
        graphicsEntities = Collections.synchronizedList(new LinkedList<GraphicsEntity>());
        this.gameWorld = gameWorld;
        
    }
    
    public GameWorld getGameWorld(){
        return gameWorld;
    }
    
    public void addObject(GraphicsEntity graphicsEntity){
        
        
        synchronized(graphicsEntities){
            graphicsEntities.add(graphicsEntity);
        }
        
    }
    
    public void RemoveObject(GraphicsEntity graphicsEntity){
        synchronized(graphicsEntities){
        
            for(int id = 0; id < graphicsEntities.size(); id++){
                
                GraphicsEntity object = graphicsEntities.get(id);
                if(object == graphicsEntity)
                    graphicsEntities.remove(id);

            }
        }
    }
    
    public void UpdateObjects(){
        
        synchronized(graphicsEntities){
            for(int id = 0; id < graphicsEntities.size(); id++){
                
                GraphicsEntity object = graphicsEntities.get(id);
                if(object.getObject().getState() == ParticularObject.DEATH){
                    graphicsEntities.remove(id);
                }
            }
        }

        //System.out.println("Camerawidth  = "+camera.getWidth());
        
    }
    
    public void draw(Graphics2D g2){
        synchronized(graphicsEntities){
            for(GraphicsEntity object: graphicsEntities)
                if(!object.getObject().isObjectOutOfCameraView()) object.draw(g2);
        }
    }
    
    
    
   
        
    
    
    
}

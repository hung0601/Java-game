/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hung.graphics;

import com.hung.iventory.Item;
import com.hung.state.GameWorld;
import java.awt.Graphics2D;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author manhh
 */
public class ItemManager {
    protected List<Item> Items;

    private GameWorld gameWorld;
    
    public ItemManager(GameWorld gameWorld){
        
        Items = Collections.synchronizedList(new LinkedList<Item>());
        this.gameWorld = gameWorld;
        
    }
    
    public GameWorld getGameWorld(){
        return gameWorld;
    }
    
    public void addObject(Item item){
        
        
        synchronized(Items){
            Items.add(item);
            
        }
        
    }
    
    public void RemoveObject(Item item){
        synchronized(Items){
        
            for(int id = 0; id < Items.size(); id++){
                
                Item object = Items.get(id);
                if(object == item)
                    Items.remove(id);

            }
        }
    }
    
    public void UpdateObjects(){
        
        synchronized(Items){
            for(int id = 0; id < Items.size(); id++){
                
                Item object = Items.get(id);
                if(object.getPickupRange().intersects(gameWorld.knightEntity.getKnight().getBoundForCollisionWithMap())){
                    object.isInInvent=true;
                    gameWorld.knightEntity.getKnight().getInvent().items.add(object);
                    gameWorld.sMessage.addMessage("+1 "+Items.get(id).getName());
                    Items.remove(id);
                }
            }
        }

        //System.out.println("Camerawidth  = "+camera.getWidth());
        
    }
    
    public void draw(Graphics2D g2){
        synchronized(Items){
            for(Item object: Items)
                 object.draw(g2,(int) object.getPosX()- (int)gameWorld.camera.getPosX(),(int) object.getPosY()- (int) gameWorld.camera.getPosY());
        }
    }
}

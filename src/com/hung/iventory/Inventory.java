/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hung.iventory;


import com.hung.gameobjects.Knight;
import com.hung.graphics.InventoryUImanager;
import java.util.ArrayList;


/**
 *
 * @author Admin
 */
public class Inventory {
    public Knight knight;
    public ArrayList<Item> items = new  ArrayList<>();
    public final int size = 35;
    public int currentSlot;
    InventoryUImanager ui;
    public Inventory(InventoryUImanager ui, Knight knight){
      this.ui = ui;
      this.setItems();
      this.knight=knight;
    }
    public void setItems(){
        items.add(new Healing(0, 0));
        items.add(new Healing(0, 0));
        items.add(new Defend(0, 0));
        items.add(new Defend(0, 0));
        items.add(new Defend(0, 0));
        items.add(new Speed(0,0));
        items.add(new Speed(0,0));
        items.add(new Speed(0,0));
        items.add(new Banana(0,0));
        items.add(new Banana(0,0));
        items.add(new Banana(0,0));
        items.add(new Apple(0,0));
        items.add(new Pear(0,0));
        items.add(new Pear(0,0));
        items.add(new Strawberry(0,0));
        items.add(new Grape(0,0));
        items.add(new Carrot(0,0));
        items.add(new Onion(0,0));
        items.add(new Onion(0,0));
        items.add(new Onion(0,0));
        items.add(new Corn(0,0));
        items.add(new Egg(0, 0));
        items.add(new Egg(0, 0));
        items.add(new Egg(0, 0));
        items.add(new Strawberry(0, 0));
        items.add(new Strawberry(0, 0));
        items.add(new Strawberry(0, 0));
        items.add(new Diamond(0, 0)); 
        items.add(new Gold(0, 0)); 
        items.add(new Mushroom(0, 0)); 
        currentSlot = items.size()-1;
    }
    
    public void PickItem(Item item){
         if (items.size() < size) {
            items.add(item);
        }
    }
    public boolean isEmpty(){
        return items.isEmpty();
    }
    public void  UseItem(int item){
        knight.getGameWorld().sMessage.addMessage("Consume 1 "+items.get(item).getName());
        items.get(item).UseItem(knight);
        items.remove(item);
        
    }
}

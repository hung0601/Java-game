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
    Knight knight;
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
//        items.add(new Healing());
//        items.add(new Defend());
//        items.add(new Speed());
//        items.add(new Banana());
//        items.add(new Apple());
//        items.add(new Pear());
//        items.add(new Strawberry());
//        items.add(new Grape());
//        items.add(new Carrot());
//        items.add(new Onion());
//        items.add(new Corn());
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

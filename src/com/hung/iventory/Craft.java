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
public class Craft {
    
    Knight knight;
    public ArrayList<Item> items = new  ArrayList<>();
    public final int size = 35;
    public void MoveTocraft(Inventory in, int it){
         if(items.size() < 3){
            items.add(in.items.get(it));      
            in.items.remove(in.items.get(it));      
         }
    }
    
    public void Undo(Inventory iv){
        if(!items.isEmpty()){
        if(items.size() < 4){
          iv.items.add(items.get(items.size()-1));
          items.remove(items.size()-1);
        } else{
          iv.items.add(items.get(items.size()-2));
          items.remove(items.size()-1);
          items.remove(items.size()-1);
        }
        }
    }
    
    public void CompleCraft(Inventory iv){
        System.out.println("Craft");
        iv.items.add(items.get(items.size()-1));
        items.remove(3);
        items.remove(2);
        items.remove(1);
        items.remove(0);
    }
    public Item Check(Item it1, Item it2, Item it3){
        if(it1.getId() == 1 && it2.getId() == 1 && it3.getId() == 1)
            return new Healing(0, 0);
        else
             if(it1.getId() == 8 && it2.getId() == 8 && it3.getId() == 8)
            return new BigPotion(0, 0);
        else
             if(it1.getId() == 5 && it2.getId() == 5 && it3.getId() == 5)
            return new BigDefend(0, 0);
        else
        if(it1.getId() == 12 && it2.getId() == 12 && it3.getId() == 12)
            return new BigSpeed(0, 0);
        else
        if(it1.getId() == 3 && it2.getId() == 3 && it3.getId() == 3)
            return new Grape(0, 0);
        else
         if(it1.getId() == 6 && it2.getId() == 6 && it3.getId() == 6)
            return new EggBasket(0, 0);
        else
         if(it1.getId() == 9 && it2.getId() == 9 && it3.getId() == 9)
            return new Corn(0, 0);
        else
             if(it1.getId() == 11 && it2.getId() == 11 && it3.getId() == 11)
            return new Cheese(0, 0);
        else
                  if(it1.getId() == 2 && it2.getId() == 2 && it3.getId() == 2)
            return new Strawberry(0, 0);
        else
        return null;
    }
    InventoryUImanager ui;
    public Craft(InventoryUImanager ui, Knight knight){
      this.ui = ui;
      this.knight=knight;
    }
}

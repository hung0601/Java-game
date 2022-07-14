/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hung.iventory;

import com.hung.graphics.ShopUIManager;
import com.hung.gameobjects.Knight;
import com.hung.graphics.InventoryUImanager;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class Shop {
    Knight knight;
    public ArrayList<Item> items = new  ArrayList<>();
    ShopUIManager ui;
    public Shop(ShopUIManager ui, Knight knight){
      this.ui = ui;
      this.setItems();
      this.knight=knight;
    }
    
    public void setItems(){
        items.add(new Banana(0, 0));
        items.add(new Apple(0, 0));
        items.add(new Pear(0, 0));
        items.add(new Strawberry(0, 0));
        items.add(new Grape(0, 0));
        items.add(new Carrot(0, 0));
        items.add(new Onion(0, 0));
        items.add(new Corn(0, 0));
//        items.add(new Cheese(0, 0));
    }
    
    public void  Buy(int item, Inventory iv){
        if(knight.getCoin() < items.get(item).coin){
            System.out.println("Not Enough Money " + knight.getCoin());
        }
        else{
            knight.setCoin(knight.getCoin() - items.get(item).coin);
             iv.items.add(items.get(item));
             items.remove(item);
        }     
    }
}

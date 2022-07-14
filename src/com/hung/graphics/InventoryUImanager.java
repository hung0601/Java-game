     /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hung.graphics;

/**
 *
 * @author Admin
 */
import com.hung.gameobjects.Knight;
import com.hung.iventory.Craft;
import com.hung.iventory.Inventory;
import com.hung.iventory.Item;
import com.hung.userinterface.GamePanel;
import java.awt.BasicStroke;
import java.awt.Graphics2D;
import  java.awt.Font;
import java.awt.Color;
import java.awt.Image;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
    


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Admin
 */
public class InventoryUImanager {
    public int  tileSize=32;
    Graphics2D g2;
    public int slotCol = 0;
    public int  slotRow = 0;
    Image keyImage;
    Font  arial_40;
    public Inventory in ;
    public Craft craft;
    public Knight kn;
    public InventoryUImanager(Inventory invent, Craft craft, Knight kn){
        
      arial_40 = new Font("Arial", Font.PLAIN, 40);      
      this.in=invent;
      this.craft = craft;
      this.kn = kn;
    }
    
    public void draw(Graphics2D g2){
//      keyImage = ImageIO.read
      this.g2 = g2;
      g2.setFont(arial_40);
      g2.setColor(Color.WHITE);
      drawInventory();
      drawChacracterScreen();
      drawCraftItem();
    }
    public void drawInventory(){
      //Frame
      int frameX = tileSize * 8;
      int frameY = tileSize * 3;
      int slotXstart = frameX + 16;
      int slotYstart = frameY + 16;
      int frameWidth = tileSize * 12;
      int frameHeight = tileSize * 10;
      drawWindow(frameX, frameY, frameWidth, frameHeight); //draw iventory
      int slotX = slotXstart;
      int slotY = slotYstart;
      //Cursor
      int cursorX = slotXstart + (tileSize * slotCol);
      int cursorY = slotYstart + (tileSize * slotRow);
      int cursorWidth = tileSize;
      int cursorHeight = tileSize;
      
      g2.setColor(Color.white);
      g2.setStroke(new BasicStroke(3));
      g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);
        for(int i = 0; i < in.items.size(); i++){
            g2.drawImage(in.items.get(i).getImage(), slotX, slotY, null);
           slotX += tileSize;
            if(i == 10 || i == 21 || i == 32 || i == 43 || i == 54 || i == 65 || i == 76 || i == 87 || i == 98) {                
                slotX = slotXstart;
                slotY += tileSize;
            }
        }
        
        //Description
        int dFrameX = frameX;
        int dFrameY = frameY + frameHeight;
        int dFrameWidth = frameWidth;
        int dFrameHeight = tileSize * 3;
        drawWindow(dFrameX, dFrameY, dFrameWidth, dFrameHeight);
        //Description Text
        int textX = dFrameX + 16;
        int textY = dFrameY + tileSize;
        g2.setFont(g2.getFont().deriveFont(24F));
        
        int itemIndex = getItemIndexOnSlot();
        if(itemIndex < in.items.size()){
           String line = in.items.get(itemIndex).getDes();
           g2.drawString(line, textX, textY);
        }      
    }
    public void drawChacracterScreen(){
        final int frameX = tileSize * 2;
        final int frameY = tileSize * 3;
        final int frameWidth = tileSize*6;
        final int frameHeight = tileSize * 10;
        drawWindow(frameX, frameY, frameWidth, frameHeight);
        
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(24F));
        
        int textX = frameX + 20;
        int textY = frameY + tileSize;
        final  int  lineHeight = 32;
        
        g2.drawString("HP", textX, textY);
        g2.drawString(String.valueOf(kn.getBlood()), textX + 120, textY);
        textY += lineHeight;
        g2.drawString("ATK", textX, textY);
        g2.drawString(String.valueOf(kn.getDamage()), textX + 120, textY);          
        textY += lineHeight;
        g2.drawString("DEF", textX, textY);
        g2.drawString(String.valueOf(kn.getDefense()), textX + 120, textY);
        textY += lineHeight;
        g2.drawString("COIN", textX, textY);
        g2.drawString(String.valueOf(kn.getCoin()), textX + 120, textY);
        textY += lineHeight;
         g2.drawString("MANA", textX, textY);
        g2.drawString(String.valueOf(kn.getMana()), textX + 120, textY);
    }
     public  void drawCraftItem(){
        final int frameX = tileSize * 20;
        final int frameY = tileSize * 3;
        final int frameWidth = tileSize*2;
        final int frameHeight = tileSize * 10;
        int slotX = frameX + 17;
        int slotY = frameY + 15;
        drawWindow(frameX, frameY, frameWidth, frameHeight);
        
      g2.setColor(Color.white);
      g2.setFont(g2.getFont().deriveFont(60F));
      g2.setStroke(new BasicStroke(3));
      g2.drawString("+", frameX+16 , frameY+92);
      g2.drawString("+", frameX+16 , frameY+172);
      g2.drawString("=", frameX+16 , frameY+252);
      g2.drawRoundRect(frameX+16, frameY+16, tileSize, tileSize, 10, 10);
      g2.drawRoundRect(frameX+16, frameY+96, tileSize, tileSize, 10, 10);
      g2.drawRoundRect(frameX+16, frameY+176, tileSize, tileSize, 10, 10);
      g2.drawRoundRect(frameX+16, frameY+256, tileSize, tileSize, 10, 10);
      
      for(int i = 0; i < craft.items.size(); i++){
           g2.drawImage(craft.items.get(i).getImage(), slotX, slotY, null);
           slotY += 80;    
        }
      if(craft.items.size() == 3){
           Item it = craft.Check(craft.items.get(0), craft.items.get(1), craft.items.get(2));
           if(it != null){
             craft.items.add(it);
             g2.drawImage(it.getImage(), slotX, slotY, null);
           }
      }
    }
    
    public int  getItemIndexOnSlot(){
         int itemIndex = slotCol + (slotRow * 11);
         return itemIndex;
    }
    public void drawWindow(int x, int y, int width, int height){
      Color c = new Color(0,0,0,0.5f);
      g2.setColor(c);
      g2.fillRoundRect(x, y, width, height, 35, 35);
      
      c = new Color(255, 255, 255);
      g2.setColor(c);
      g2.setStroke(new BasicStroke(5));
      g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25); 
    }
    
    
}



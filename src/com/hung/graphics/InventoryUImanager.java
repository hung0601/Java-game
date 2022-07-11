     /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hung.graphics;

/**
 *
 * @author Admin
 */
import com.hung.iventory.Inventory;
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
    public InventoryUImanager(Inventory invent){
        
      arial_40 = new Font("Arial", Font.PLAIN, 40);      
      this.in=invent;
    }
    
    public void draw(Graphics2D g2){
//      keyImage = ImageIO.read
      this.g2 = g2;
      g2.setFont(arial_40);
      g2.setColor(Color.WHITE);
      drawInventory();
      
    }
    public void drawInventory(){
      //Frame
      int frameX = tileSize * 18;
      int frameY = tileSize;
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
    
    public int  getItemIndexOnSlot(){
         int itemIndex = slotCol + (slotRow * 11);
         return itemIndex;
    }
    public void drawWindow(int x, int y, int width, int height){
      Color c = Color.black;
      g2.setColor(c);
      g2.fillRoundRect(x, y, width, height, 35, 35);
      
      c = new Color(255, 255, 255);
      g2.setColor(c);
      g2.setStroke(new BasicStroke(5));
      g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25); 
    }
    
    
}



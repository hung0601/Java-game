/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hung.userinterface;

import com.hung.effect.CacheDataLoader;
import com.hung.effect.FrameImage;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author manhh
 */
public class UI {
    GamePanel gp;
    Graphics g2;
    public int commandNum=0;
    public UI(GamePanel gp){
        this.gp=gp;
    }
    
    public void drawTitle(Graphics2D g2){
        BufferedImage image= CacheDataLoader.getInstance().getFrameImage("idle_down1").getImage();
        try {
            image= FrameImage.resizeImage(image,image.getWidth()*2,image.getHeight()*2);
        } catch (IOException ex) {
            Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.g2=g2;
        g2.setColor(new Color(0,0,0));
        g2.fillRect(0, 0, GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT);      
                        
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(3));
        
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,96F));
        String text= "KNIGHT";
        int x=getXforCenterText(text);
        int y=gp.tileSize*3;
        
        g2.setColor(Color.gray);
        g2.drawString(text, x+3, y+3);
        
        g2.setColor(Color.white);
        g2.drawString(text,x,y);
        
        x=GameFrame.SCREEN_WIDTH/2;
        y+=gp.tileSize*3;
        g2.drawImage(image, x-image.getWidth()/2, y-image.getHeight()/2, null);
        
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,30F));
        
        
        
        text = "NEW GAME";
        x=getXforCenterText(text);
        y+=gp.tileSize*5;
        g2.drawString(text, x, y);
        
        if(commandNum==0){
            g2.drawString(">", x-gp.tileSize, y);
        }
        
        text = "QUIT";
        x=getXforCenterText(text);
        y+=gp.tileSize*2;
        g2.drawString(text, x, y);
        if(commandNum==1){
            g2.drawString(">", x-gp.tileSize, y);
        }
    }
    
    
    public int getXforCenterText(String text){
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x= GameFrame.SCREEN_WIDTH/2- length/2; 
        return x;
    }

    void drawPauseGame(Graphics2D bufG2D) {
        this.g2=g2;
        g2.setColor(new Color(0,0,0,0.5f));
        g2.fillRect(0, 0, GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT);      
                        
        g2.setColor(Color.white);
        
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,96F));
        String text= "KNIGHT";
        int x=getXforCenterText(text);
        int y=gp.tileSize*3;
        
        g2.setColor(Color.gray);
        g2.drawString(text, x+3, y+3);
        
        g2.setColor(Color.white);
        g2.drawString(text,x,y);
        
        
        
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,30F));
        
        
        
        text = "RESUME";
        x=getXforCenterText(text);
        y+=gp.tileSize*5;
        g2.drawString(text, x, y);
        
        if(commandNum==0){
            g2.drawString(">", x-gp.tileSize, y);
        }
        
        text = "NEW GAME";
        x=getXforCenterText(text);
        y+=gp.tileSize*2;
        g2.drawString(text, x, y);
        if(commandNum==1){
            g2.drawString(">", x-gp.tileSize, y);
        }
        
        text = "QUIT";
        x=getXforCenterText(text);
        y+=gp.tileSize*2;
        g2.drawString(text, x, y);
        if(commandNum==2){
            g2.drawString(">", x-gp.tileSize, y);
        }
    }
    
    
    
}
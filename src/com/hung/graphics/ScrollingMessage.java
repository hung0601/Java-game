/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hung.graphics;

import com.hung.state.GameWorld;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 *
 * @author manhh
 */
public class ScrollingMessage {
    
    public GameWorld gameWorld;
    ArrayList<String> message = new ArrayList<>();
    ArrayList<Integer> messageCounter = new ArrayList<>();
    
    public ScrollingMessage(GameWorld gameWorld){
        this.gameWorld= gameWorld;
    }
    public void addMessage(String text){
        message.add(text);
        messageCounter.add(0);
    }
    
    public void drawMessage(Graphics2D g2){
        int x= 32;
        int y= 150;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,20F));
        
        for(int i=0;i<message.size(); i++){
            if(message.get(i)!= null){
                g2.setColor(Color.white);
                g2.drawString(message.get(i),x,y);
                int counter= messageCounter.get(i)+1;
                messageCounter.set(i, counter);
                y+=30;
                
                if(messageCounter.get(i)>100){
                    message.remove(i);
                    messageCounter.remove(i);
                }
            }
        }
        
    }
}

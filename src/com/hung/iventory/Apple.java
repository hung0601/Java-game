/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hung.iventory;

import com.hung.effect.FrameImage;
import com.hung.gameobjects.Knight;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

/**
 *
 * @author Admin
 */
public class Apple extends Item{
    public Apple(float x,float y){
       super(x,y);
       try {
            BufferedImage temImage = ImageIO.read(new  File("Data/Item.png"));
            this.image = temImage.getSubimage(0, 448, 32, 32);
            frame = new FrameImage("Apple", image);
            des = frame.getName() + ": Heal 5 HP";
            this.name="Apple";
            coin = 20;
            id = 1;
        } catch (Exception e) {
               }       
     }
    
    @Override
    public void UseItem(Knight knight){
        if(knight.getBlood()+5>100) knight.setBlood(100);
        else  knight.setBlood(knight.getBlood()+5);
        
    }

   
}

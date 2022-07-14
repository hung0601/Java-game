/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hung.iventory;

import com.hung.gameobjects.Knight;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import com.hung.effect.FrameImage;

/**
 *
 * @author Admin
 */
public class Egg extends Item{
     public Egg(float x,float y){
         super(x,y);
            try {
               BufferedImage temImage = ImageIO.read(new  File("Data/Item.png"));
               this.image = temImage.getSubimage(192, 480, 32, 32);
               frame = new FrameImage("Egg", image);
                des = frame.getName() + ": Increase 2 HP";
                this.name="Egg";
                coin = 5;
                id = 6;
                } catch (Exception e) {
                }        
             }
          
          @Override
    public void UseItem(Knight knight){
         if(knight.getBlood()+5>100) knight.setBlood(100);
        else  knight.setBlood(knight.getBlood()+2);
    }
}

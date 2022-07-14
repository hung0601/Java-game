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
public class Banana extends Item{
          public Banana(float x,float y){
             super(x,y);
            try {
               BufferedImage temImage = ImageIO.read(new  File("Data/Item.png"));
               this.image = temImage.getSubimage(32, 448, 32, 32);
               frame = new FrameImage("Banana", image);
                des = frame.getName() + ": Increase 3 ATK";
                this.name="Banana";
                coin = 20;
                id = 2;
                } catch (Exception e) {
                }        
             }
          
          @Override
    public void UseItem(Knight knight){
         //Increase 3 ATK
         knight.setDamage(knight.getDamage()+3);
    }

   
}
    


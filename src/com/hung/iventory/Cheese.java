/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hung.iventory;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import com.hung.effect.FrameImage;
import com.hung.gameobjects.Knight;


/**
 *
 * @author Admin
 */
public class Cheese extends Item{
    public Cheese(float x,float y){
        super(x,y);
            try {
               BufferedImage temImage = ImageIO.read(new  File("Data/Item.png"));
               this.image = temImage.getSubimage(224, 480, 32, 32);
               frame = new FrameImage("Cheese", image);
                des = frame.getName() + ": Increase 15 ATK";
                this.name="Cheese";
                coin = 200;
                } catch (Exception e) {
                }        
             }
          
          @Override
   public void UseItem(Knight knight){
         //Increase 5 ATK
         knight.setDamage(knight.getDamage()+15);
    }
}

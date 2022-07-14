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
public class Pear extends Item{
    public Pear(float x,float y){
        super(x,y);
       try {
            BufferedImage temImage = ImageIO.read(new  File("Data/Item.png"));
            this.image = temImage.getSubimage(64, 448, 32, 32);
            frame = new FrameImage("Pear", image);
             des = frame.getName() + ": Heal 10 HP";
             this.name="Pear";
             coin = 50;
             id = 10;
        } catch (Exception e) {
               }     
     }
    @Override
    public void UseItem(Knight knight){
         //Heal 10 HP
         if(knight.getBlood()+5>100) knight.setBlood(100);
        else  knight.setBlood(knight.getBlood()+10);
    }

    
}

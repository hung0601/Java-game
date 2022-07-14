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
public class BigPotion extends Item{
    public BigPotion(float x,float y){
        super(x,y);
       try {
            BufferedImage temImage = ImageIO.read(new  File("Data/Item.png"));
            this.image = temImage.getSubimage(128, 608, 32, 32);
            frame = new FrameImage("Big Potion", image);
             des = frame.getName() + ": Healing 100 HP";
            coin = 500;
        } catch (Exception e) {
               }
     }
    
    @Override
    public void UseItem(Knight knight){
         if(knight.getBlood()+100>100) knight.setBlood(100);
        else  knight.setBlood(knight.getBlood()+100);
    }
}

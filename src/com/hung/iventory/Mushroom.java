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
public class Mushroom extends Item{
     public Mushroom(float x,float y){
        super(x,y);
       try {
            BufferedImage temImage = ImageIO.read(new  File("Data/Item.png"));
            this.image = temImage.getSubimage(384, 448, 32, 32);
            frame = new FrameImage("Mushroom", image);
            des = frame.getName() + ": Heal 30 MN";
            this.name="Mushroom";
            coin = 100;
            id = 13;
        } catch (Exception e) {
               }       
     }
    
    @Override
    public void UseItem(Knight knight){
         //Heal 30 MN
         if(knight.getMana()+30>100) knight.setMana(100);
        else  knight.setMana(knight.getMana()+30);
    }
}

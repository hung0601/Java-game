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
public class Onion extends Item{
     public Onion(float x,float y){
         super(x,y);
       try {
            BufferedImage temImage = ImageIO.read(new  File("Data/Item.png"));
            this.image = temImage.getSubimage(256, 448, 32, 32);
            frame = new FrameImage("Onion", image);
              des = frame.getName() + ": Increase 1 DEF";
              this.name="Onion";
              coin = 2;
              id = 9;
        } catch (Exception e) {
               }
     }
    @Override
    public void UseItem(Knight knight){
         //Increase 1 DEF
         knight.setDefense(knight.getDefense()+1);
    }

    
}

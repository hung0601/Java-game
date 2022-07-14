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
public class Defend extends Item{
    public Defend(float x,float y){
        super(x,y);
       try {
            BufferedImage temImage = ImageIO.read(new  File("Data/Item.png"));
            this.image = temImage.getSubimage(32, 288, 32, 32);
            frame = new FrameImage("Defend", image);
             des = frame.getName() + ": Increas 10 DEF";
             this.name="Degend Drug";
             coin = 75;
             id = 5;
        } catch (Exception e) {
               }
     }
    
    @Override
    public void UseItem(Knight knight){
         //Increas 10 DEF
         knight.setDefense(knight.getDefense()+10);
    }

    
}

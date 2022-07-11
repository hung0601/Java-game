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
public class Carrot extends Item{
    public Carrot(float x,float y){
        super(x,y);
       try {
            BufferedImage temImage = ImageIO.read(new  File("Data/Item.png"));
            this.image = temImage.getSubimage(192, 448, 32, 32);
            frame = new FrameImage("Carot", image);
            des = frame.getName() + ": Heal 7 HP";
        } catch (Exception e) {
               }       
     }
    
    @Override
    public void UseItem(Knight knight){
         //Heal 7 HP
    }

   
    

    
}

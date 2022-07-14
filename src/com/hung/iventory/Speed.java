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
public class Speed extends Item{
    public Speed(float x,float y){
        super(x,y);
      try {
            BufferedImage temImage = ImageIO.read(new  File("Data/Item.png"));
            this.image = temImage.getSubimage(64, 288, 32, 32);
//             System.out.println(image1);
            frame = new FrameImage("Acceleration ", image);
            des = frame.getName() + ": Increase Speed";
            this.name="Speed Drug";
            id = 12;
            coin = 75;
        } catch (Exception e) { 
    }
    }
    @Override
    public void UseItem(Knight knight){
         //Increase Speed
    }

    
}

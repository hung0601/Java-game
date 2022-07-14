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
public class Healing extends  Item{
    public  Healing(float x,float y){
        super(x,y);
        try {
            BufferedImage temImage = ImageIO.read(new  File("Data/Item.png"));
            this.image = temImage.getSubimage(0, 288, 32, 32);
//             System.out.println(image1);
            frame = new FrameImage("Small Potions", image);
            des = frame.getName() + ": Heal 15 HP";
            this.name="Healing Drug";
            coin = 75;
            id = 8;
        } catch (Exception e) {
    }
    }
    @Override
    public void UseItem(Knight knight){
         //Heal 15 HP
         if(knight.getBlood()+15>100) knight.setBlood(100);
        else  knight.setBlood(knight.getBlood()+15);
    }

    
}
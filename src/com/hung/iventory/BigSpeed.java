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
public class BigSpeed extends Item{
    public BigSpeed(float x,float y){
        super(x,y);
       try {
            BufferedImage temImage = ImageIO.read(new  File("Data/Item.png"));
            this.image = temImage.getSubimage(192, 608, 32, 32);
            frame = new FrameImage("Big Speed", image);
             des = frame.getName() + ": Increas 20 Speed";
             coin = 500;
        } catch (Exception e) {
               }
     }
    
    @Override
    public void UseItem(Knight knight){
         //Increase 5 ATK
         knight.setDamage(knight.getDamage()+5);
    }
}

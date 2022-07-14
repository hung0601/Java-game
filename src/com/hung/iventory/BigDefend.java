/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hung.iventory;

import com.hung.gameobjects.Knight;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import com.hung.effect.FrameImage;

/**
 *
 * @author Admin
 */
public class BigDefend extends Item{
    public BigDefend(float x,float y){
        super(x,y);
       try {
            BufferedImage temImage = ImageIO.read(new  File("Data/Item.png"));
            this.image = temImage.getSubimage(160, 608, 32, 32);
            frame = new FrameImage("Big Defend", image);
             des = frame.getName() + ": Increas 20 DEF";
             coin = 500;
             id = 3;
        } catch (Exception e) {
               }
     }
    
    @Override
     public void UseItem(Knight knight){
         knight.setDefense(knight.getDefense()+20);
    }
}

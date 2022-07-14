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
public class Diamond extends Item{
    public Diamond(float x,float y){
        super(x,y);
       try {
            BufferedImage temImage = ImageIO.read(new  File("Data/Item.png"));
            this.image = temImage.getSubimage(128, 544, 32, 32);
            frame = new FrameImage("Diamond", image);
             des = frame.getName() + ": Earn 1000 coint";
             this.name="Diamond";
        } catch (Exception e) {
               }
     }
    
    @Override
    public void UseItem(Knight knight){
         knight.setCoin(knight.getCoin()+1000);
    }
}

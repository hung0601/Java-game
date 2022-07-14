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
public class EggBasket extends Item{
    public EggBasket(float x,float y){
        super(x,y);
            try {
               BufferedImage temImage = ImageIO.read(new  File("Data/Item.png"));
               this.image = temImage.getSubimage(160, 480, 32, 32);
               frame = new FrameImage("Egg Basket", image);
                des = frame.getName() + ": Increase 8 HP";
                this.name="Egg Basket";
                } catch (Exception e) {
                }        
             }
          
          @Override
    public void UseItem(Knight knight){
         if(knight.getBlood()+5>100) knight.setBlood(100);
        else  knight.setBlood(knight.getBlood()+8);
    }
}

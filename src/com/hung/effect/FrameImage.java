/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hung.effect;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 *
 * @author manhh
 */
public class FrameImage {
    private String name;
    private BufferedImage image;
    
    
    public FrameImage(){
    name=null;
    image=null;
    }
    public FrameImage(String name, BufferedImage image){
    this.name= name;
    this.image= image;
    }
    
    public FrameImage(FrameImage frameImage){
        
        image= new BufferedImage(frameImage.getImagewidth(),frameImage.getImageHeight(),frameImage.getImage().getType());
        Graphics g=image.getGraphics();
        g.drawImage(frameImage.image, 0, 0, null);
        name = frameImage.name;
    }
    
    
    
    public void draw(Graphics2D g2, int x, int y){
    g2.drawImage(image, x-image.getWidth()/2,y-image.getHeight()/2,null);
    }
    
    public static BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) throws IOException {
    BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TRANSLUCENT);
    Graphics2D graphics2D = resizedImage.createGraphics();
    graphics2D.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
    graphics2D.dispose();
    return resizedImage;
}
    public int getImagewidth(){
        return image.getWidth();
    }
    public int getImageHeight(){
        return image.getHeight();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
    
}

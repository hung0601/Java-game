/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hung.iventory;

import com.hung.effect.FrameImage;
import com.hung.gameobjects.GameObject;
import com.hung.gameobjects.Knight;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author Admin
 */
public abstract class Item {
    public boolean isInInvent=false;
    protected FrameImage frame;
    protected int dmg;
    protected String des = " ";
    protected String name = " ";
    protected BufferedImage image;
    protected int coin;
    protected int id;

    public int getCoin() {
        return coin;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    private float posX;
    private float posY;
    public final float width=40;
    public final float height=40;
    
    public  Item(float posX, float posY){
        this.posX= posX;
        this.posY= posY;
        
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public float getPosX() {
        return posX;
    }

    public void setPosX(float posX) {
        this.posX = posX;
    }

    public float getPosY() {
        return posY;
    }

    public void setPosY(float posY) {
        this.posY = posY;
    }

   
    public abstract void UseItem(Knight knight);
    
    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
    
    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public FrameImage getFrame() {
        return frame;
    }

    public void setFrame(FrameImage frame) {
        this.frame = frame;
    }

    public int getDmg() {
        return dmg;
    }

    public void setDmg(int dmg) {
        this.dmg = dmg;
    }
    
    public void draw(Graphics2D g2, int posX, int posY){
        frame.draw(g2,  posX,  posY);
    }
    
    public Rectangle getPickupRange(){
        Rectangle bound = new Rectangle();
        bound.x = (int) (getPosX() - width/2);
        bound.y = (int) (getPosY() - height/2);
        bound.width =  (int)width;
        bound.height = (int)height;
        return bound;
    }
    
}

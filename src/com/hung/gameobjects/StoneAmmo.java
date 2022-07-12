/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hung.gameobjects;

import com.hung.effect.Animation;
import com.hung.effect.CacheDataLoader;
import static com.hung.gameobjects.ParticularObject.ALIVE;
import static com.hung.gameobjects.ParticularObject.DOWN_DIR;
import static com.hung.gameobjects.ParticularObject.LEFT_DIR;
import static com.hung.gameobjects.ParticularObject.RIGHT_DIR;
import static com.hung.gameobjects.ParticularObject.UP_DIR;
import com.hung.state.GameWorld;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author manhh
 */
public class StoneAmmo extends Skill{
    public Animation fly_stone;
    public int direction;
    public StoneAmmo(float x, float y, GameWorld gameWorld,int dir,int masterDamage,int targetX,int targetY) {
        super(x, y, 10, 10, 10+masterDamage, gameWorld);        
        this.direction=dir;
        setRemainTime(5000*1000);
        setBeginTime(System.nanoTime());
        
        
        float checkdir;
        float bq= (float)Math.sqrt(Math.pow(targetX-x,2)+Math.pow(targetY-y,2));
        checkdir=Math.abs((targetY-y)/bq);
        setSpeedX(8*(targetX-x)/bq);
        setSpeedY(8*(targetY-y)/bq);
        
        
        
        fly_stone = CacheDataLoader.getInstance().getAnimation("stone_ammo");
    }
    
    
    
    @Override
    public Rectangle getBoundForCollisionWithEnemy() {
        // TODO Auto-generated method stub
        Rectangle bound = new Rectangle();
        bound.x = (int) (getPosX() - (getWidth()/2));
        bound.y = (int) (getPosY() - (getHeight()/2));
        bound.width = (int) getWidth();
        bound.height = (int) getHeight();
        return bound;
    }

    

    @Override
    public void Update() {
            // TODO Auto-generated method stub
        
        setPosX(getPosX() + getSpeedX());
        setPosY(getPosY() + getSpeedY());
       
        
        if(isRemain()){
        ParticularObject object = getGameWorld().particularObjectManager.getCollisionWidthEnemyObject(this);
        if(object!=null && object.getState() == ALIVE){
            setBlood(0);
            object.beHurt(getDamage());
            System.out.println("Bullet set behurt for enemy");
        }
        }
        
    }

    @Override
    public void draw(Graphics2D g2) {
        
        //drawBoundForCollisionWithEnemy(g2);
        
            fly_stone.Update(System.nanoTime());
            fly_stone.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
                
        
        
    }

    @Override
    public void attack() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

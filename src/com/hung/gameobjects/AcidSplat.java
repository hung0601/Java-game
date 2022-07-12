/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hung.gameobjects;

import com.hung.effect.Animation;
import com.hung.effect.CacheDataLoader;
import static com.hung.gameobjects.ParticularObject.ALIVE;
import com.hung.state.GameWorld;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author manhh
 */
public class AcidSplat extends Skill{
    public Animation exploy;
    public AcidSplat(float x, float y, GameWorld gameWorld,int masterDamage) {
        super(x, y, 20, 20, 10+masterDamage, gameWorld);
        setRemainTime(700*1000);
        setBeginTime(System.nanoTime());
        setSpeedX(2);
        setSpeedY(2);
        
        exploy = CacheDataLoader.getInstance().getAnimation("acid_splat");
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
            
         setWidth(getWidth()+getSpeedX());
         setHeight(getHeight()+getSpeedY());
        if(isRemain()){
        ParticularObject object = getGameWorld().particularObjectManager.getCollisionWidthEnemyObject(this);
        if(object!=null && object.getState() == ALIVE){
            //setBlood(0);
            object.beHurt(getDamage());
            System.out.println("Bullet set behurt for enemy");
        }
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        drawBoundForCollisionWithEnemy(g2);
        exploy.Update(System.nanoTime());
        exploy.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
              
    }

    @Override
    public void attack() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}

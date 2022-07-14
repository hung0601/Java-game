/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hung.gameobjects;

import com.hung.state.GameWorld;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author manhh
 */
public class SwordAttack extends Skill{
    
    
    public SwordAttack(float x, float y, GameWorld gameWorld,int masterDamage) {
        super(x, y, 40, 60, 10+masterDamage, gameWorld);
        setRemainTime(500*1000);
        setBeginTime(System.nanoTime());
        setSpeedX(0);
        setSpeedY(0);
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
        if(isRemain()){
        ParticularObject object = getGameWorld().particularObjectManager.getCollisionWidthEnemyObject(this);
        if(object!=null && object.getState() == ALIVE){
            setBlood(0);
            object.beHurt(getDamage());
            System.out.println("Bullet set behurt for enemy");
            if(object.getDefense()==0) getGameWorld().knightEntity.getKnight().increaseMana(30);
        }
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        //drawBoundForCollisionWithEnemy(g2);
    }

    @Override
    public void attack() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    
}

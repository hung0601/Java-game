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
public class StabSword extends Skill{
    
    public Animation attackLeft2, attackRight2, attackUp2, attackDown2;
    public int direction;
    public StabSword(float x, float y, GameWorld gameWorld,int dir,int masterDamage) {
        super(x, y, 30, 60, 10+masterDamage, gameWorld);        
        this.direction=dir;
        setRemainTime(5000*1000);
        setBeginTime(System.nanoTime());
        switch(dir){
            case DOWN_DIR:
                setSpeedX(0);
                setSpeedY(4);
                break;
            case UP_DIR:
                setSpeedX(0);
                setSpeedY(-4);
                break;
            case LEFT_DIR:
                setSpeedX(-4);
                setSpeedY(0);
                break;
            case RIGHT_DIR:
                setSpeedX(4);
                setSpeedY(0);
                break;
        }
        
        
        attackLeft2 = CacheDataLoader.getInstance().getAnimation("skill_left");
        attackRight2 = CacheDataLoader.getInstance().getAnimation("skill_right");
        attackUp2 = CacheDataLoader.getInstance().getAnimation("skill_up");
        attackDown2 = CacheDataLoader.getInstance().getAnimation("skill_down");
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
        if((System.nanoTime()-getBeginTime())/1000>600000){
        setPosX(getPosX() + getSpeedX());
        setPosY(getPosY() + getSpeedY());
        }
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
//        drawBoundForCollisionWithEnemy(g2);
        if((System.nanoTime()-getBeginTime())/1000>650000){
        switch(direction){
            case DOWN_DIR:
                attackDown2.Update(System.nanoTime());
                attackDown2.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
                break;
            case UP_DIR:
                attackUp2.Update(System.nanoTime());
                attackUp2.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
               
                break;
            case LEFT_DIR:
                attackLeft2.Update(System.nanoTime());
                attackLeft2.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
                break;
            case RIGHT_DIR:
                attackRight2.Update(System.nanoTime());
                attackRight2.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
                break;
        }
        }
    }

    @Override
    public void attack() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
}

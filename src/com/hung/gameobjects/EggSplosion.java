/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hung.gameobjects;

import static com.hung.gameobjects.ParticularObject.ALIVE;
import static com.hung.gameobjects.ParticularObject.DOWN_DIR;
import static com.hung.gameobjects.ParticularObject.ENEMY_TEAM;
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
public class EggSplosion extends Creature{
    public static final int RUNSPEED = 0;
    private Rectangle range;

    public EggSplosion(float x, float y, GameWorld gameWorld) {
        super(x, y, 30, 30, 40, gameWorld);
        
//        shooting1 = CacheDataLoader.getInstance().getSound("bluefireshooting");
//        hurtingSound = CacheDataLoader.getInstance().getSound("megamanhurt");
        setDamage(10);
        setTeamType(ENEMY_TEAM);
        setTimeForBeHurt(1000000); 
        range= new Rectangle();
        isAttacking=false;
        //setPosY(y+10);
        
    }
    
   
    
    
    public boolean isInAttackRange(){
        int x= (int)getGameWorld().knightEntity.getKnight().getPosX();
        int y= (int)getGameWorld().knightEntity.getKnight().getPosY();
        
        if(x>getPosX()-100&& x<getPosX()+100 && y>getPosY()-100&& y<getPosY()+100){
            return true;
        }
        else return false;
    }
    
    
    public boolean isAttackColdownOver(){
        if((System.nanoTime()-lastAttackTime)/1000>3000000){
            return true;
        }
        else return false;
    }
    
    @Override
    public void attack() {
        if(!isAttacking && isAttackColdownOver() && getState()==ALIVE){
            System.out.println("attack");
            //stopRun();
            Skill skill = new AcidSplat(getPosX(), getPosY(), getGameWorld(),getDamage());
            skill.setTeamType(getTeamType());
            getGameWorld().skillManager.addObject(skill);
            lastAttackTime=System.nanoTime();
            isAttacking = true;
        }
    }
   
    
    
    
    
    @Override
    public void Update() {
        
        if(isInAttackRange() && !isAttacking && isAttackColdownOver()) attack();
        super.Update();
        
        
        
        
    }
    
    

    @Override
    public void run() {
        if(!isAttacking){
        switch (getDirection()) {
            case LEFT_DIR:
                setSpeedX(-RUNSPEED);
                break;
            case RIGHT_DIR:
                setSpeedX(RUNSPEED);
                break;
            case UP_DIR:
                setSpeedY(-RUNSPEED);
                break;
            case DOWN_DIR:
                setSpeedY(RUNSPEED);
                break;
            default: break;
        }
        }
        
    }

    @Override
    public void stopRun() {
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

    
}

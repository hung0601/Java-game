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
public class Goblin extends Creature{
    public static final int RUNSPEED = 1;
    private Rectangle range;

    public Goblin(float x, float y, GameWorld gameWorld) {
        super(x, y, 20, 56, 30, gameWorld);
        
//        shooting1 = CacheDataLoader.getInstance().getSound("bluefireshooting");
//        hurtingSound = CacheDataLoader.getInstance().getSound("megamanhurt");
        setDamage(5);
        setTeamType(ENEMY_TEAM);
        setTimeForBeHurt(1000000); 
        range= new Rectangle();
        
    }
    
    
    public void setRange(int x, int y, int width, int height){
        range.x=x;
        range.y=y;
        range.width=width;
        range.height=height;
        
    }
    public boolean isOutOfRange(){
        Rectangle col=getBoundForCollisionWithMap();
        if(col.x<= range.x || col.x+col.width>= range.x+range.width ||
                col.y<= range.y || col.y+col.height>= range.y+range.height){
            return true;
        }
        else return false;
    }
    
    public boolean isInTargetRange(){
        int x= (int)getGameWorld().knightEntity.getKnight().getPosX();
        int y= (int)getGameWorld().knightEntity.getKnight().getPosY();
        
        if(x>getPosX()-400&& x<getPosX()+400 && y>getPosY()-400&& y<getPosY()+400){
            return true;
        }
        else return false;
    }
    
    public boolean isInAttackRange(){
        int x= (int)getGameWorld().knightEntity.getKnight().getPosX();
        int y= (int)getGameWorld().knightEntity.getKnight().getPosY();
        
        if(x>getPosX()-350&& x<getPosX()+350 && y>getPosY()-350&& y<getPosY()+350){
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
            int x= (int)getGameWorld().knightEntity.getKnight().getPosX();
            int y= (int)getGameWorld().knightEntity.getKnight().getPosY();
            System.out.println("attack");
            stopRun();
            Skill skill = new StoneAmmo(getPosX(), getPosY(), getGameWorld(),getDirection(),getDamage(),x,y);
            skill.setTeamType(getTeamType());
            getGameWorld().skillManager.addObject(skill);
            lastAttackTime=System.nanoTime();
            isAttacking = true;
        }
    }
    
    
    
    @Override
    public void Update() {
        if(isAttacking && (System.nanoTime()-lastAttackTime)/1000>1000000){
            
        }
        
        //System.out.println(getTimeForBeHurt());
        float checkdir;
        int x= (int)getGameWorld().knightEntity.getKnight().getPosX();
        int y= (int)getGameWorld().knightEntity.getKnight().getPosY();
        float bq= (float)Math.sqrt(Math.pow(x-getPosX(),2)+Math.pow(y-getPosY(),2));
        
        
        if(isInAttackRange() && !isAttacking && getState()==ALIVE) {
            
            stopRun();
            attack();
        }
        else if(isInTargetRange() && getState()==ALIVE && !isAttacking){
            checkdir=Math.abs((y-getPosY())/bq);
            setSpeedX(RUNSPEED*(x-getPosX())/bq);
            setSpeedY(RUNSPEED*(y-getPosY())/bq);
            
            
            
            if(x-getPosX()<0 && checkdir>=0 && checkdir< Math.sqrt(0.5)) setDirection(LEFT_DIR);
            else if(x-getPosX()>0 && checkdir>=0 && checkdir< Math.sqrt(0.5)) setDirection(RIGHT_DIR);
            else if(y-getPosY()>0 && checkdir>=Math.sqrt(0.5) && checkdir<=1 ) setDirection(DOWN_DIR);
            else if(y-getPosY()<0 && checkdir>=Math.sqrt(0.5) && checkdir<=1) setDirection(UP_DIR);
            
            
        }
        else if(isOutOfRange() && getState()==ALIVE && !isAttacking){
            x=range.x+range.width/2;
            y=range.y+range.height/2;
            bq= (float)Math.sqrt(Math.pow(x-getPosX(),2)+Math.pow(y-getPosY(),2));
            checkdir=Math.abs((y-getPosY())/bq);
            if(x-getPosX()<0 && checkdir>=0 && checkdir< Math.sqrt(0.5)) setDirection(LEFT_DIR);
            else if(x-getPosX()>0 && checkdir>=0 && checkdir< Math.sqrt(0.5)) setDirection(RIGHT_DIR);
            else if(y-getPosY()>0 && checkdir>=Math.sqrt(0.5) && checkdir<=1 ) setDirection(DOWN_DIR);
            else if(y-getPosY()<0 && checkdir>=Math.sqrt(0.5) && checkdir<=1) setDirection(UP_DIR);
            
            setSpeedX(RUNSPEED*(x-getPosX())/bq);
            setSpeedY(RUNSPEED*(y-getPosY())/bq);
            
        }
        //System.out.println(getState());

        super.Update();
        
        
        
        
    }
    
    public void draw(Graphics2D g2) {
        drawBoundForCollisionWithMap(g2);
        drawBoundForCollisionWithEnemy(g2);
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

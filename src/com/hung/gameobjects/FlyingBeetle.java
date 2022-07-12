/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hung.gameobjects;

import com.hung.state.GameWorld;
import static com.hung.gameobjects.ParticularObject.ALIVE;
import static com.hung.gameobjects.ParticularObject.DOWN_DIR;
import static com.hung.gameobjects.ParticularObject.ENEMY_TEAM;
import static com.hung.gameobjects.ParticularObject.LEFT_DIR;
import static com.hung.gameobjects.ParticularObject.RIGHT_DIR;
import static com.hung.gameobjects.ParticularObject.UP_DIR;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author manhh
 */
public class FlyingBeetle extends Creature{
    public static final int RUNSPEED = 1;
    private Rectangle range;

    public FlyingBeetle(float x, float y, GameWorld gameWorld) {
        super(x, y, 20, 20, 10, gameWorld);
        
//        shooting1 = CacheDataLoader.getInstance().getSound("bluefireshooting");
//        hurtingSound = CacheDataLoader.getInstance().getSound("megamanhurt");
        setDamage(10);
        setTeamType(ENEMY_TEAM);
        setTimeForBeHurt(1000000); 
        range= new Rectangle();
        isAttacking=false;
        
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
        
        if(x>getPosX()-130&& x<getPosX()+130 && y>getPosY()-130&& y<getPosY()+130){
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
    public void Update() {
        
        ParticularObject object = getGameWorld().particularObjectManager.getCollisionWidthEnemyObject(this);
        if(object!=null &&isAttackColdownOver()){
                    
                stopRun();
                
                        // switch state to fey if object die
                        
                        
                //System.out.println("eat damage.... from collision with enemy........ "+object.getDamage());
                object.beHurt(getDamage());
                
                    
                }
        else{
        
        
        //System.out.println(getTimeForBeHurt());
        float checkdir;
        int x= (int)getGameWorld().knightEntity.getKnight().getPosX();
        int y= (int)getGameWorld().knightEntity.getKnight().getPosY();
        float bq= (float)Math.sqrt(Math.pow(x-getPosX(),2)+Math.pow(y-getPosY(),2));
        
        
        if(isInTargetRange() && getState()==ALIVE ){
            checkdir=Math.abs((y-getPosY())/bq);
            setSpeedX(RUNSPEED*(x-getPosX())/bq);
            setSpeedY(RUNSPEED*(y-getPosY())/bq);
            
            
            
            if(x-getPosX()<0 && checkdir>=0 && checkdir< Math.sqrt(0.5)) setDirection(LEFT_DIR);
            else if(x-getPosX()>0 && checkdir>=0 && checkdir< Math.sqrt(0.5)) setDirection(RIGHT_DIR);
            else if(y-getPosY()>0 && checkdir>=Math.sqrt(0.5) && checkdir<=1 ) setDirection(DOWN_DIR);
            else if(y-getPosY()<0 && checkdir>=Math.sqrt(0.5) && checkdir<=1) setDirection(UP_DIR);
            
            
        }
        else if(isOutOfRange() && getState()==ALIVE ){
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
        }
        //System.out.println(getState());

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

    @Override
    public void attack() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

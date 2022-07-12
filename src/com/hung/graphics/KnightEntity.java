/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hung.graphics;

import com.hung.effect.Animation;
import com.hung.effect.CacheDataLoader;
import com.hung.gameobjects.GameObject;
import com.hung.state.GameWorld;
import com.hung.gameobjects.Knight;
import com.hung.gameobjects.ParticularObject;
import static com.hung.gameobjects.ParticularObject.ALIVE;
import static com.hung.gameobjects.ParticularObject.BEHURT;
import static com.hung.gameobjects.ParticularObject.DEATH;
import static com.hung.gameobjects.ParticularObject.DOWN_DIR;
import static com.hung.gameobjects.ParticularObject.FEY;
import static com.hung.gameobjects.ParticularObject.LEFT_DIR;
import static com.hung.gameobjects.ParticularObject.RIGHT_DIR;
import static com.hung.gameobjects.ParticularObject.UP_DIR;
import java.awt.Graphics2D;


/**
 *
 * @author manhh
 */
public class KnightEntity extends GraphicsEntity{
    public Animation attackLeft2, attackRight2, attackUp2, attackDown2;
 
    private Knight knight;
    public KnightEntity(int x,int y,GameWorld gameWorld){
        super(x,y,gameWorld);
        this.knight=new Knight(x,y,gameWorld);
        setObject(knight);
        runLeft = CacheDataLoader.getInstance().getAnimation("walk_left");
        runRight = CacheDataLoader.getInstance().getAnimation("walk_right");
        runUp = CacheDataLoader.getInstance().getAnimation("walk_up");
        runDown = CacheDataLoader.getInstance().getAnimation("walk_down");
          
        
        idleLeft = CacheDataLoader.getInstance().getAnimation("idle_left");
        idleRight = CacheDataLoader.getInstance().getAnimation("idle_right");
        idleUp = CacheDataLoader.getInstance().getAnimation("idle_up");
        idleDown = CacheDataLoader.getInstance().getAnimation("idle_down");
        
        attackLeft = CacheDataLoader.getInstance().getAnimation("attack_left");
        attackRight = CacheDataLoader.getInstance().getAnimation("attack_right");
        attackUp = CacheDataLoader.getInstance().getAnimation("attack_up");
        attackDown = CacheDataLoader.getInstance().getAnimation("attack_down");
        
        attackLeft2 = CacheDataLoader.getInstance().getAnimation("main_attack_left");
        attackRight2 = CacheDataLoader.getInstance().getAnimation("main_attack_right");
        attackUp2 = CacheDataLoader.getInstance().getAnimation("main_attack_up");
        attackDown2 = CacheDataLoader.getInstance().getAnimation("main_attack_down");
        
        deadLeft = CacheDataLoader.getInstance().getAnimation("death_left");
        deadRight = CacheDataLoader.getInstance().getAnimation("death_right");
        deadUp = CacheDataLoader.getInstance().getAnimation("death_up");
        deadDown = CacheDataLoader.getInstance().getAnimation("death_down");
        
        behurtLeft = CacheDataLoader.getInstance().getAnimation("hurt_left");
        behurtRight = CacheDataLoader.getInstance().getAnimation("hurt_right");
        behurtUp = CacheDataLoader.getInstance().getAnimation("hurt_up");
        behurtDown = CacheDataLoader.getInstance().getAnimation("hurt_down");
    }
    
    public Knight getKnight(){
        return  this.knight;
    }
    
    public void draw(Graphics2D g2) {
        
switch(knight.getState()){
        
            case ALIVE:
                    
                    
                        if(knight.getSpeedX() > 0){
                            runRight.Update(System.nanoTime());
                    
                            runRight.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
                            //if(runRight.getCurrentFrame() == 1) runRight.setIgnoreFrame(0);
                        }else if(knight.getSpeedX() < 0){
                            runLeft.Update(System.nanoTime());
                            runLeft.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
                            //if(runLeft.getCurrentFrame() == 1) runLeft.setIgnoreFrame(0);
                        }else if(knight.getSpeedY() < 0){
                            runUp.Update(System.nanoTime());
                            
                            runUp.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
                            if(runUp.getCurrentFrame() == 1) runUp.setIgnoreFrame(0);
                        }else if(knight.getSpeedY() > 0){
                            runDown.Update(System.nanoTime());
                            
                            runDown.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
                            if(runDown.getCurrentFrame() == 1) runDown.setIgnoreFrame(0);
                        }else{
                            if(knight.getDirection() == RIGHT_DIR){
                                if(knight.isAttacking){
                                    attackRight.Update(System.nanoTime());
                                    attackRight.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
                                    if(attackRight.isLastFrame()){
                                        knight.isAttacking=false;
                                    }
                                }
                                else if(knight.isAttacking2){
                                    attackRight2.Update(System.nanoTime());
                                    attackRight2.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
                                    if(attackRight2.isLastFrame()){
                                        knight.isAttacking2=false;
                                    }
                                }
                                else{
                                attackRight2.reset();
                                attackRight.reset();
                                idleRight.Update(System.nanoTime());
                                idleRight.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
                                } 
                            }else if(knight.getDirection() == LEFT_DIR){
                                if(knight.isAttacking){
                                    attackLeft.Update(System.nanoTime());
                                    attackLeft.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
                                    if(attackLeft.isLastFrame()){
                                        knight.isAttacking=false;
                                    }
                                }
                                else if(knight.isAttacking2){
                                    attackLeft2.Update(System.nanoTime());
                                    attackLeft2.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
                                    if(attackLeft2.isLastFrame()){
                                        knight.isAttacking2=false;
                                    }
                                }
                                else{
                                attackLeft2.reset();
                                attackLeft.reset();
                                idleLeft.Update(System.nanoTime());
                                idleLeft.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
                                } 
                            }else if(knight.getDirection() == UP_DIR){
                                
                                if(knight.isAttacking){
                                    attackUp.Update(System.nanoTime());
                                    attackUp.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
                                    if(attackUp.isLastFrame()){
                                        knight.isAttacking=false;
                                    }
                                }
                                else if(knight.isAttacking2){
                                    attackUp2.Update(System.nanoTime());
                                    attackUp2.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
                                    if(attackUp2.isLastFrame()){
                                        knight.isAttacking2=false;
                                    }
                                }
                                else{
                                attackUp2.reset();
                                attackUp.reset();
                                idleUp.Update(System.nanoTime());
                                idleUp.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
                                }
                            }
                            else{
                                
                                if(knight.isAttacking){
                                    attackDown.Update(System.nanoTime());
                                    attackDown.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
                                    if(attackDown.isLastFrame()){
                                        knight.isAttacking=false;
                                    }
                                }
                                else if(knight.isAttacking2){
                                    attackDown2.Update(System.nanoTime());
                                    attackDown2.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
                                    if(attackDown2.isLastFrame()){
                                        knight.isAttacking2=false;
                                    }
                                }
                                else{
                                attackDown2.reset();
                                attackDown.reset();
                                idleDown.Update(System.nanoTime());
                                idleDown.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
                                }
                            }
                        }            
                    
                
                
                break;
            
            case BEHURT:
                switch(knight.getDirection()){
                    case LEFT_DIR:
                        
                           
                        behurtLeft.Update(System.nanoTime());
                        behurtLeft.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
                        if(behurtLeft.isLastFrame()){
                            if(knight.getBlood()<=0){
                                
                                knight.setState(FEY);
                            }else{
                            knight.setState(ALIVE);
                            behurtLeft.reset();
                            }
                        }        
                        break;
                    case RIGHT_DIR:
                        behurtRight.Update(System.nanoTime());
                        behurtRight.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
                        if(behurtRight.isLastFrame()){
                            if(knight.getBlood()<=0){
                                knight.setState(FEY);
                            }else{
                            knight.setState(ALIVE);
                            behurtRight.reset();
                            }
                        }        
                        break;
                        
                    case UP_DIR:
                        
                        behurtUp.Update(System.nanoTime());
                        behurtUp.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
                        if(behurtUp.isLastFrame()){
                            if(knight.getBlood()<=0){
                                knight.setState(FEY);
                            }else{
                            knight.setState(ALIVE);
                            behurtUp.reset();
                            }
                        }        
                        break;
                        
                    case DOWN_DIR:
                        behurtDown.Update(System.nanoTime());
                        behurtDown.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
                        if(behurtDown.isLastFrame()){
                            if(knight.getBlood()<=0){
                                knight.setState(FEY);
                            }else{
                            knight.setState(ALIVE);
                            behurtDown.reset();
                            }
                        }        
                        break;
                
                
                }
                break;
             
            case FEY:
                switch(knight.getDirection()){
                    case LEFT_DIR:
                        deadLeft.Update(System.nanoTime());
                        deadLeft.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
                        if(deadLeft.isLastFrame()){
                            knight.setState(DEATH);
                            deadLeft.reset();
                        }        
                        break;
                    case RIGHT_DIR:
                        deadRight.Update(System.nanoTime());
                        deadRight.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
                        if(deadRight.isLastFrame()){                         
                            knight.setState(DEATH);
                            deadRight.reset();
                        }        
                        break;
                        
                    case UP_DIR:
                        
                        deadUp.Update(System.nanoTime());
                        deadUp.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
                        if(deadUp.isLastFrame()){
                            knight.setState(DEATH);
                            deadUp.reset();
                        }        
                        break;
                        
                    case DOWN_DIR:
                        deadDown.Update(System.nanoTime());
                        deadDown.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
                        if(deadDown.isLastFrame()){
                            knight.setState(DEATH);
                            deadDown.reset();
                        }        
                        break;
                
                
                }
                break;

        }
        
        //knight.drawBoundForCollisionWithMap(g2);
        //knight.drawBoundForCollisionWithEnemy(g2);
    }
    
    @Override
    public void Update() {
        knight.Update();
    }
    
    
}

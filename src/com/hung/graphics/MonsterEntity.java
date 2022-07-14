/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hung.graphics;

import com.hung.effect.CacheDataLoader;
import com.hung.gameobjects.Creature;
import com.hung.state.GameWorld;
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
public class MonsterEntity extends GraphicsEntity{
    private Creature creature;
    public MonsterEntity(ParticularObject object ,GameWorld gameWorld){
        super(object.getPosX(),object.getPosY(),gameWorld);
        setObject(object);
        creature= (Creature)object;

    }
    
    
    @Override
    public void draw(Graphics2D g2) {
        
    switch(creature.getState()){
        
            case ALIVE:
            
                    
                    
                        if(creature.getSpeedX() > 0 && creature.getDirection() == RIGHT_DIR){
                            runRight.Update(System.nanoTime());
                    
                            runRight.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
                            //if(runRight.getCurrentFrame() == 1) runRight.setIgnoreFrame(0);
                        }else if(creature.getSpeedX() < 0 && creature.getDirection() == LEFT_DIR){
                            runLeft.Update(System.nanoTime());
                            
                            runLeft.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
                            //if(runLeft.getCurrentFrame() == 1) runLeft.setIgnoreFrame(0);
                        }else if(creature.getSpeedY() < 0 && creature.getDirection() == UP_DIR){
                            runUp.Update(System.nanoTime());
                            
                            runUp.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
                            if(runUp.getCurrentFrame() == 1) runUp.setIgnoreFrame(0);
                        }else if(creature.getSpeedY() > 0 && creature.getDirection() == DOWN_DIR){
                            runDown.Update(System.nanoTime());
                            
                            runDown.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
                            if(runDown.getCurrentFrame() == 1) runDown.setIgnoreFrame(0);
                        }else{
                            if(creature.getDirection() == RIGHT_DIR){
                                if(creature.isAttacking){
                                    attackRight.Update(System.nanoTime());
                                    attackRight.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
                                    if(attackRight.isLastFrame()){
                                        creature.isAttacking=false;
                                    }
                                }
                                else{
                                if(attackRight !=null) attackRight.reset();
                                idleRight.Update(System.nanoTime());
                                idleRight.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
                                } 
                            }else if(creature.getDirection() == LEFT_DIR){
                                if(creature.isAttacking){
                                    attackLeft.Update(System.nanoTime());
                                    attackLeft.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
                                    if(attackLeft.isLastFrame()){
                                        creature.isAttacking=false;
                                    }
                                }
                                else{
                                if(attackLeft !=null) attackLeft.reset();
                                idleLeft.Update(System.nanoTime());
                                idleLeft.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
                                } 
                            }else if(creature.getDirection() == UP_DIR){
                                
                                if(creature.isAttacking){
                                    attackUp.Update(System.nanoTime());
                                    attackUp.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
                                    if(attackUp.isLastFrame()){
                                        creature.isAttacking=false;
                                    }
                                }
                                else{
                                if(attackUp !=null) attackUp.reset();
                                idleUp.Update(System.nanoTime());
                                idleUp.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
                                } 
                            }
                            else{
                                
                                if(creature.isAttacking){
                                    attackDown.Update(System.nanoTime());
                                    attackDown.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
                                    if(attackDown.isLastFrame()){
                                        creature.isAttacking=false;
                                    }
                                }
                                else{
                                if(attackDown !=null) attackDown.reset();
                                idleDown.Update(System.nanoTime());
                                idleDown.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
                                } 
                            }
                        }            
                    
                
                
                break;
            
            case BEHURT:
                if(behurtLeft!=null){
                switch(creature.getDirection()){
                    case LEFT_DIR:
                        
                           
                        behurtLeft.Update(System.nanoTime());
                        behurtLeft.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
                        if(behurtLeft.isLastFrame()){
                            if(creature.getBlood()<=0){
                                
                                creature.setState(FEY);
                            }else{
                            creature.setState(ALIVE);
                            behurtLeft.reset();
                            }
                        }        
                        break;
                    case RIGHT_DIR:
                        behurtRight.Update(System.nanoTime());
                        behurtRight.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
                        if(behurtRight.isLastFrame()){
                            if(creature.getBlood()<=0){
                                creature.setState(FEY);
                            }else{
                            creature.setState(ALIVE);
                            behurtRight.reset();
                            }
                        }        
                        break;
                        
                    case UP_DIR:
                        
                        behurtUp.Update(System.nanoTime());
                        behurtUp.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
                        if(behurtUp.isLastFrame()){
                            if(creature.getBlood()<=0){
                                creature.setState(FEY);
                            }else{
                            creature.setState(ALIVE);
                            behurtUp.reset();
                            }
                        }        
                        break;
                        
                    case DOWN_DIR:
                        behurtDown.Update(System.nanoTime());
                        behurtDown.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
                        if(behurtDown.isLastFrame()){
                            if(creature.getBlood()<=0){
                                creature.setState(FEY);
                            }else{
                            creature.setState(ALIVE);
                            behurtDown.reset();
                            }
                        }        
                        break;
                
                
                }
                }
                break;
             
            case FEY:
                if(deadLeft!=null){
                switch(creature.getDirection()){
                    case LEFT_DIR:
                        deadLeft.Update(System.nanoTime());
                        deadLeft.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
                        if(deadLeft.isLastFrame()){
                            creature.setState(DEATH);
                            deadLeft.reset();
                        }        
                        break;
                    case RIGHT_DIR:
                        deadRight.Update(System.nanoTime());
                        deadRight.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
                        if(deadRight.isLastFrame()){                         
                            creature.setState(DEATH);
                            deadRight.reset();
                        }        
                        break;
                        
                    case UP_DIR:
                        
                        deadUp.Update(System.nanoTime());
                        deadUp.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
                        if(deadUp.isLastFrame()){
                            creature.setState(DEATH);
                            deadUp.reset();
                        }        
                        break;
                        
                    case DOWN_DIR:
                        deadDown.Update(System.nanoTime());
                        deadDown.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
                        if(deadDown.isLastFrame()){
                            creature.setState(DEATH);
                            deadDown.reset();
                        }        
                        break;
                
                
                }
                }else{
                    creature.setState(DEATH);
                }
                break;

        }
        
        //creature.drawBoundForCollisionWithMap(g2);
//        creature.drawBoundForCollisionWithEnemy(g2);
    }

    @Override
    public void Update() {
        creature.Update();
    }
    
}

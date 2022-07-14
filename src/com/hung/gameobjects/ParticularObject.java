/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hung.gameobjects;

import com.hung.state.GameWorld;
import com.hung.effect.Animation;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author manhh
 */
public abstract class ParticularObject extends GameObject{
    
    public static final int LEAGUE_TEAM = 1;
    public static final int ENEMY_TEAM = 2;
    
    public static final int LEFT_DIR = 0;
    public static final int RIGHT_DIR = 1;
    public static final int UP_DIR = 2;
    public static final int DOWN_DIR = 3;

    public static final int ALIVE = 0;
    public static final int BEHURT = 1;
    public static final int FEY = 2;
    public static final int DEATH = 3;
    
    private int state = ALIVE;
    public static final int MAXMANA = 100;
    
    private float width;
    private float height;
    private float speedX;
    private float speedY;
    private int blood;
    
    private int damage;
    private int defense=0;
    private int mana=0;

    
    
    private int direction;
    
    private int teamType;
    
    private long startBeHurtTime;

    
    private long timeForBeHurt;

   
    
     private long beginTime;
    private long remainTime;

    public ParticularObject(float x, float y, float width, float height, int blood, GameWorld gameWorld){

        // posX and posY are the middle coordinate of the object
        super(x, y, gameWorld);
        setWidth(width);
        setHeight(height);
        setBlood(blood);
        
        direction = RIGHT_DIR;

    }
    
    
    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }
    public long getStartBeHurtTime() {
        return startBeHurtTime;
    }

    public void setStartBeHurtTime(long startBeHurtTime) {
        this.startBeHurtTime = startBeHurtTime;
    }
    
    public long getTimeForBeHurt() {
        return timeForBeHurt;
    }

    public void setTimeForBeHurt(long timeForBeHurt) {
        this.timeForBeHurt = timeForBeHurt;
    }
    public void setState(int state){
        this.state = state;
    }
    
    public int getState(){
        return state;
    }
    
    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }
    
    public void setDamage(int damage){
            this.damage = damage;
    }
    
    public int getDamage(){
            return damage;
    }

    
    public void setTeamType(int team){
        teamType = team;
    }
    
    public int getTeamType(){
        return teamType;
    }
    
   

    public void setSpeedX(float speedX){
        this.speedX = speedX;
    }

    public float getSpeedX(){
        return speedX;
    }

    public void setSpeedY(float speedY){
        this.speedY = speedY;
    }

    public float getSpeedY(){
        return speedY;
    }

    public void setBlood(int blood){
        if(blood>=0)
                this.blood = blood;
        else this.blood = 0;
    }

    public int getBlood(){
        return blood;
    }

    public void setWidth(float width){
        this.width = width;
    }

    public float getWidth(){
        return width;
    }

    public void setHeight(float height){
        this.height = height;
    }

    public float getHeight(){
        return height;
    }
    
    public void setDirection(int dir){
        direction = dir;
    }
    
    public int getDirection(){
        return direction;
    }
    
    public double getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(long beginTime) {
        this.beginTime = beginTime;
    }
    public double getRemainTime() {
        return remainTime;
    }

    public void setRemainTime(long remainTime) {
        this.remainTime = remainTime;
    }
    
    public boolean isRemain(){
    if((System.nanoTime()-beginTime)/1000<=remainTime) return true;
    else return false;
    }
    
    
    
    public abstract void attack();
    
    
    
    public Rectangle getBoundForCollisionWithMap(){
        Rectangle bound = new Rectangle();
        bound.x = (int) (getPosX() - (getWidth()/2));
        bound.y = (int) (getPosY() + (getHeight()/2)-5);
        bound.width = (int) getWidth();
        bound.height = 5;
        return bound;
    }
    


    public void beHurt(int damgeEat){
        if(state==ALIVE){
        int takenDamage=damgeEat-defense;
        if(takenDamage<0) takenDamage=0;
        setStartBeHurtTime(System.nanoTime());
        setBlood(getBlood() - takenDamage);
        state = BEHURT;
        System.out.println("dr");
        //hurtingCallback();
        }
        
    }

    @Override
    public void Update(){
                
        switch(state){
            case ALIVE:
                
                
                break;
                
            case BEHURT:
                if(getBlood() <= 0) state = FEY;
        
//                if(behurtBackAnim == null){
//                    if(getBlood() == 0)
//                            state = FEY;
//                    
//                } else {
//                    behurtForwardAnim.Update(System.nanoTime());
//                    if(behurtForwardAnim.isLastFrame())
//                        behurtForwardAnim.reset();
//                        
//                }
                //setSpeedX(0);
                //setSpeedY(0);
                //System.out.println(System.nanoTime()-getStartBeHurtTime());
                
//                if((System.nanoTime()-getStartBeHurtTime())/1000>getTimeForBeHurt()){
//                state= ALIVE;
//                }
                
                break;
                
            case FEY:
                
                //state = DEATH;
                
                break;
            
            case DEATH:
                
                
                break;
                
            
        }
        
    }
    public boolean isObjectOutOfCameraView(){
        if(getPosX() - getGameWorld().camera.getPosX() > getGameWorld().camera.getWidthView() ||
                getPosX() - getGameWorld().camera.getPosX() < -50
            ||getPosY() - getGameWorld().camera.getPosY() > getGameWorld().camera.getHeightView()
                    ||getPosY() - getGameWorld().camera.getPosY() < -50)
            return true;
        else return false;
    }

    public void drawBoundForCollisionWithMap(Graphics2D g2){
        Rectangle rect = getBoundForCollisionWithMap();
        g2.setColor(Color.BLUE);
        g2.drawRect(rect.x - (int) getGameWorld().camera.getPosX(), rect.y - (int) getGameWorld().camera.getPosY(), rect.width, rect.height);
    }

    public void drawBoundForCollisionWithEnemy(Graphics2D g2){
        Rectangle rect = getBoundForCollisionWithEnemy();
        g2.setColor(Color.RED);
        g2.drawRect(rect.x - (int) getGameWorld().camera.getPosX(), rect.y - (int) getGameWorld().camera.getPosY(), rect.width, rect.height);
    }

    public abstract Rectangle getBoundForCollisionWithEnemy();

    
    public void hurtingCallback(){};
}

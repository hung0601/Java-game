/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hung.gameobjects;

import com.hung.state.GameWorld;
import com.hung.effect.Animation;
import com.hung.effect.CacheDataLoader;
import com.hung.iventory.Craft;
import com.hung.iventory.Inventory;
import com.hung.iventory.Shop;
import java.applet.AudioClip;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author manhh
 */
public class Knight extends Creature {

    public static final int RUNSPEED = 3;
    private Inventory invent;
    private Craft craft;
    public Shop shop;
    private int coin;

    public int getCoin() {
        return coin;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }
    public Craft getCraft() {
        return craft;
    }

    public void setCraft(Craft craft) {
        this.craft = craft;
    }

    public boolean isIsAttacking2() {
        return isAttacking2;
    }

    public void setIsAttacking2(boolean isAttacking2) {
        this.isAttacking2 = isAttacking2;
    }
    public boolean isAttacking2=false;

    public Knight(float x, float y, GameWorld gameWorld) {
        super(x, y, 20, 56, 100, gameWorld);
        
//        shooting1 = CacheDataLoader.getInstance().getSound("bluefireshooting");
//        hurtingSound = CacheDataLoader.getInstance().getSound("megamanhurt");
        setDefense(2);
        setTeamType(LEAGUE_TEAM);
        setTimeForBeHurt(2000000); 
        invent= new Inventory(getGameWorld().inventManager,this);
        craft = new Craft(getGameWorld().inventManager, this);
        shop = new Shop(getGameWorld().shopManager, this);
        setDamage(5);
        coin = 500;
        setMana(MAXMANA);
        
    }
    
    public Inventory getInvent() {
        return invent;
    }

    public void setInvent(Inventory invent) {
        this.invent = invent;
    }
    
    public void increaseMana(int increase){
        int i= getMana()+increase;
        if(i>MAXMANA) setMana(MAXMANA);
        else setMana(i);
    
    }
    @Override
    public void Update() {
        super.Update();
        
        
        
        
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
    public void attack() {
    
        if(!isAttacking && !isAttacking2){
            stopRun();
            Skill skill = new SwordAttack(getPosX(), getPosY(), getGameWorld(),getDamage());
            switch(getDirection()){
                case LEFT_DIR:
                    skill.setPosX(skill.getPosX()-20);
                    break;
                case RIGHT_DIR:
                    skill.setPosX(skill.getPosX()+20);
                    break;
                case UP_DIR:
                    skill.setPosY(skill.getPosY()-28);
                    skill.setHeight(40);
                    skill.setWidth(50);
                    break;
                case DOWN_DIR:
                    skill.setPosY(skill.getPosY()+28);
                    skill.setHeight(40);
                    skill.setWidth(50);
                    break;
                    
            }
            skill.setTeamType(getTeamType());
            getGameWorld().skillManager.addObject(skill);
            lastAttackTime=System.nanoTime();
            isAttacking = true;
            
            
        }
         
            
    }
    
    
   public void attack2() {
    
        if(!isAttacking && !isAttacking2 && getMana()>=15){
            setMana(getMana()-15);
            stopRun();
            if(getDirection()==UP_DIR || getDirection()==DOWN_DIR){
                Skill skill = new StabSword(getPosX(), getPosY(), getGameWorld(),DOWN_DIR,getDamage());
                skill.setTeamType(getTeamType());
                getGameWorld().skillManager.addObject(skill);
            
                Skill skill2 = new StabSword(getPosX(), getPosY(), getGameWorld(),UP_DIR,getDamage());
                skill2.setTeamType(getTeamType());
                getGameWorld().skillManager.addObject(skill2);
            
            }else{
                Skill skill = new StabSword(getPosX(), getPosY(), getGameWorld(),LEFT_DIR,getDamage());
                skill.setTeamType(getTeamType());
                getGameWorld().skillManager.addObject(skill);
            
                Skill skill2 = new StabSword(getPosX(), getPosY(), getGameWorld(),RIGHT_DIR,getDamage());
                skill2.setTeamType(getTeamType());
                getGameWorld().skillManager.addObject(skill2);
            
            }
            lastAttackTime=System.nanoTime();
            isAttacking2=true;
            
            
        }
         
            
    }
    
    public void stopAttack(){
        this.isAttacking=false;
    }
    
    @Override
    public void hurtingCallback(){
        System.out.println("Call back hurting");
        //hurtingSound.play();
    }

    

}

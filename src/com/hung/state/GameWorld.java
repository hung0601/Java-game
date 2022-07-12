/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hung.state;

import com.hung.effect.CacheDataLoader;
import com.hung.gameobjects.Camera;
import com.hung.gameobjects.FlyingBeetle;
import com.hung.gameobjects.ParticularObject;
import com.hung.gameobjects.ParticularObjectManager;
import com.hung.gameobjects.PhysicalMap;
import com.hung.gameobjects.SkillManager;
import com.hung.gameobjects.SwordSkeleton;
import com.hung.graphics.GraphicsEntityManager;
import com.hung.graphics.InventoryUImanager;
import com.hung.graphics.ItemManager;
import com.hung.graphics.KnightEntity;
import com.hung.graphics.MonsterEntity;
import com.hung.iventory.*;
import com.hung.userinterface.GameFrame;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;


public class GameWorld {
    
    
    
   public int maptype;
   public boolean isInventOpen=false;
    
   public ParticularObjectManager particularObjectManager;
   public SkillManager skillManager;
   public GraphicsEntityManager graphicsEntityManager;
   public KnightEntity knightEntity;
   public PhysicalMap physicalMap;
   public Camera camera;
   
   public InventoryUImanager inventManager;
   public ItemManager itemmanager;
   
   MonsterEntity swordSkeEntity1;
   MonsterEntity flyBeetleEntity1;
   
   private BufferedImage image=null;
   private BufferedImage health=null;
   private BufferedImage bar_border=null;
   private int bar_leng;
   

    
    public GameWorld(int m_type){
        
        maptype= m_type;
        
       try {
           image=ImageIO.read(new File("data/bar.png"));
       } catch (IOException ex) {
           Logger.getLogger(GameWorld.class.getName()).log(Level.SEVERE, null, ex);
       }
       
       bar_border=image.getSubimage(0, 0, 384, 64);
       health=image.getSubimage(0, 64, 384, 64);
        
        particularObjectManager= new ParticularObjectManager(this);
        skillManager= new SkillManager(this);
        graphicsEntityManager= new GraphicsEntityManager(this);
        
        
        physicalMap= new PhysicalMap(0,0,maptype,this);
        camera= new Camera(0,0,GameFrame.SCREEN_WIDTH,GameFrame.SCREEN_HEIGHT,this);
        
        knightEntity= new KnightEntity(640,224,this);
        inventManager= new InventoryUImanager(knightEntity.getKnight().getInvent());
        itemmanager= new ItemManager(this);
        itemmanager.addObject(new Apple(800,200));
        itemmanager.addObject(new Apple(800,300));
        itemmanager.addObject(new Apple(800,244));
        itemmanager.addObject(new Apple(800,500));
        itemmanager.addObject(new Apple(100,244));
        itemmanager.addObject(new Apple(900,244));
        
        
        graphicsEntityManager.addObject(knightEntity);
        particularObjectManager.addObject(knightEntity.getKnight());
        
        
        
        swordSkeEntity1=  initSwordSkeletonEntity();
        graphicsEntityManager.addObject(swordSkeEntity1);
        particularObjectManager.addObject(swordSkeEntity1.getObject());
        
        flyBeetleEntity1=  initFlyingBeetleEntity();
        graphicsEntityManager.addObject(flyBeetleEntity1);
        particularObjectManager.addObject(flyBeetleEntity1.getObject());
        
        
        
    }
    
    
    public void Update(){
        skillManager.UpdateObjects();
        particularObjectManager.UpdateObjects();
        graphicsEntityManager.UpdateObjects();
        camera.Update();
        itemmanager.UpdateObjects();
        //swordSke1.Update();
        
    }
    
    public void Render(Graphics2D g2){
        bar_leng=knightEntity.getKnight().getBlood()*385/100;
        physicalMap.draw(g2);
        graphicsEntityManager.draw(g2);
        skillManager.draw(g2);
        //swordSke1.draw(g2);
        g2.drawImage(bar_border,0, 0, null);
        g2.drawImage(health, 64, 0,bar_leng , health.getHeight(), null);
        
        itemmanager.draw(g2);
        if(isInventOpen==true) inventManager.draw(g2);
        
        
    }    
    
    
    
    
    
    public MonsterEntity initSwordSkeletonEntity(){
        SwordSkeleton swordSke=initSwordSkeleton(160,192,416,96);
        MonsterEntity ssEntity= new MonsterEntity(swordSke,this);
        
        ssEntity.runLeft = CacheDataLoader.getInstance().getAnimation("ske_walk_left");
        ssEntity.runRight = CacheDataLoader.getInstance().getAnimation("ske_walk_right");
        ssEntity.runUp = CacheDataLoader.getInstance().getAnimation("ske_walk_up");
        ssEntity.runDown = CacheDataLoader.getInstance().getAnimation("ske_walk_down");
          
        
        ssEntity.idleLeft = CacheDataLoader.getInstance().getAnimation("ske_idle_left");
        ssEntity.idleRight = CacheDataLoader.getInstance().getAnimation("ske_idle_right");
        ssEntity.idleUp = CacheDataLoader.getInstance().getAnimation("ske_idle_up");
        ssEntity.idleDown = CacheDataLoader.getInstance().getAnimation("ske_idle_down");
        
        ssEntity.attackLeft = CacheDataLoader.getInstance().getAnimation("ske_attack_left");
        ssEntity.attackRight = CacheDataLoader.getInstance().getAnimation("ske_attack_right");
        ssEntity.attackUp = CacheDataLoader.getInstance().getAnimation("ske_attack_up");
        ssEntity.attackDown = CacheDataLoader.getInstance().getAnimation("ske_attack_down");
        
        ssEntity.behurtLeft = CacheDataLoader.getInstance().getAnimation("ske_hurt_left");
        ssEntity.behurtRight = CacheDataLoader.getInstance().getAnimation("ske_hurt_right");
        ssEntity.behurtUp = CacheDataLoader.getInstance().getAnimation("ske_hurt_up");
        ssEntity.behurtDown = CacheDataLoader.getInstance().getAnimation("ske_hurt_down");
        
        ssEntity.deadLeft = CacheDataLoader.getInstance().getAnimation("ske_death_left");
        ssEntity.deadRight = CacheDataLoader.getInstance().getAnimation("ske_death_right");
        ssEntity.deadUp = CacheDataLoader.getInstance().getAnimation("ske_death_up");
        ssEntity.deadDown = CacheDataLoader.getInstance().getAnimation("ske_death_down");
        
        return ssEntity;
    }
    public MonsterEntity initFlyingBeetleEntity(){
        FlyingBeetle flyBeetle=initFlyingBeetle(160,192,416,96);
        MonsterEntity ssEntity= new MonsterEntity(flyBeetle,this);
        
        ssEntity.runLeft = CacheDataLoader.getInstance().getAnimation("beetle_move_left");
        ssEntity.runRight = CacheDataLoader.getInstance().getAnimation("beetle_move_right");
        ssEntity.runUp = CacheDataLoader.getInstance().getAnimation("beetle_move_up");
        ssEntity.runDown = CacheDataLoader.getInstance().getAnimation("beetle_move_down");
          
        
        ssEntity.idleLeft = CacheDataLoader.getInstance().getAnimation("beetle_left");
        ssEntity.idleRight = CacheDataLoader.getInstance().getAnimation("beetle_right");
        ssEntity.idleUp = CacheDataLoader.getInstance().getAnimation("beetle_up");
        ssEntity.idleDown = CacheDataLoader.getInstance().getAnimation("beetle_down");
        
//        ssEntity.attackLeft = CacheDataLoader.getInstance().getAnimation("attack_left");
//        ssEntity.attackRight = CacheDataLoader.getInstance().getAnimation("attack_right");
//        ssEntity.attackUp = CacheDataLoader.getInstance().getAnimation("attack_up");
//        ssEntity.attackDown = CacheDataLoader.getInstance().getAnimation("attack_down");
        return ssEntity;
    }
    
    
    public SwordSkeleton initSwordSkeleton(int x,int y, int w,int h){
        SwordSkeleton ske;
        ske= new SwordSkeleton(x+w/2,y+h/2,this);
        ske.setRange(x,y,w,h);
        ske.setDirection(ParticularObject.RIGHT_DIR);
        ske.run();
        return ske;
    }
    public FlyingBeetle initFlyingBeetle(int x,int y, int w,int h){
        FlyingBeetle flyBeetle;
        flyBeetle= new FlyingBeetle(x+w/2,y+h/2,this);
        flyBeetle.setRange(x,y,w,h);
        flyBeetle.setDirection(ParticularObject.LEFT_DIR);
        flyBeetle.run();
        return flyBeetle;
    }
    
    
    
    
}

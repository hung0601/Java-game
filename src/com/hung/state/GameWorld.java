/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hung.state;

import com.hung.graphics.ShopUIManager;
import com.hung.effect.CacheDataLoader;
import com.hung.gameobjects.Camera;
import com.hung.gameobjects.EggSplosion;
import com.hung.gameobjects.FlyingBeetle;
import com.hung.gameobjects.Goblin;
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
import com.hung.graphics.ScrollingMessage;
import com.hung.iventory.*;
import com.hung.userinterface.GameFrame;
import com.hung.userinterface.GamePanel;
import java.awt.Color;
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
   public boolean isShop = false;
   public ParticularObjectManager particularObjectManager;
   public SkillManager skillManager;
   public GraphicsEntityManager graphicsEntityManager;
   public KnightEntity knightEntity;
   public PhysicalMap physicalMap;
   public Camera camera;
   
   public InventoryUImanager inventManager;
   public ShopUIManager shopManager;
   public ItemManager itemmanager;
   
   public ScrollingMessage sMessage;
   
   MonsterEntity swordSkeEntity1;
   MonsterEntity swordSkeEntity2;
   MonsterEntity swordSkeEntity3;
   MonsterEntity swordSkeEntity4;
   MonsterEntity swordSkeEntity5;
   
   MonsterEntity goblinEntity1;
   MonsterEntity goblinEntity2;
   MonsterEntity goblinEntity3;
   
   MonsterEntity flyBeetleEntity1;
   MonsterEntity flyBeetleEntity2;
   MonsterEntity flyBeetleEntity3;
   MonsterEntity flyBeetleEntity4;
   MonsterEntity flyBeetleEntity5;
   MonsterEntity flyBeetleEntity6;
   
   
   MonsterEntity egg1;
   MonsterEntity egg2;
   MonsterEntity egg3;
   MonsterEntity egg4;
   
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
       
       sMessage= new ScrollingMessage(this);
        
        particularObjectManager= new ParticularObjectManager(this);
        skillManager= new SkillManager(this);
        graphicsEntityManager= new GraphicsEntityManager(this);
        
        
        physicalMap= new PhysicalMap(0,0,maptype,this);
        camera= new Camera(0,0,GameFrame.SCREEN_WIDTH,GameFrame.SCREEN_HEIGHT,this);
        
        knightEntity= new KnightEntity(128,288,this);
        inventManager= new InventoryUImanager(knightEntity.getKnight().getInvent(), knightEntity.getKnight().getCraft(), knightEntity.getKnight());
        shopManager = new ShopUIManager(knightEntity.getKnight().shop);
        itemmanager= new ItemManager(this);
        
        
        graphicsEntityManager.addObject(knightEntity);
        particularObjectManager.addObject(knightEntity.getKnight());
        
        
        
        
        
        
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
       
        physicalMap.draw(g2);
        graphicsEntityManager.draw(g2);
        skillManager.draw(g2);
        //swordSke1.draw(g2);
        
        itemmanager.draw(g2);
        sMessage.drawMessage(g2);
        drawStatus(g2);
        if(isInventOpen==true) inventManager.draw(g2);
        if(isShop == true) shopManager.draw(g2);
        
    }    
    
    public void drawStatus(Graphics2D g2){
        bar_leng=knightEntity.getKnight().getBlood()*385/100;
        int mamaLengt=(int) knightEntity.getKnight().getMana()*300/100;
        g2.drawImage(bar_border,0, 0, null);
        g2.drawImage(health, 64, 0,bar_leng , health.getHeight(), null);
        g2.setColor(new Color(0,0,0,0.5f));
        g2.fillRect(64, 50, 300, 10);
        g2.setColor(Color.blue);
        g2.fillRect(64, 50, mamaLengt, 10);
    }
    public void initEnemyMap1(){
       
        
        flyBeetleEntity1=  initFlyingBeetleEntity(288,244,320,288);
        graphicsEntityManager.addObject(flyBeetleEntity1);
        particularObjectManager.addObject(flyBeetleEntity1.getObject());
        
        flyBeetleEntity2=  initFlyingBeetleEntity(704,192,448,224);
        graphicsEntityManager.addObject(flyBeetleEntity2);
        particularObjectManager.addObject(flyBeetleEntity2.getObject());
        
        
        flyBeetleEntity3=  initFlyingBeetleEntity(288,244,320,288);
        graphicsEntityManager.addObject(flyBeetleEntity3);
        particularObjectManager.addObject(flyBeetleEntity3.getObject());
        
        flyBeetleEntity4=  initFlyingBeetleEntity(704,192,448,224);
        graphicsEntityManager.addObject(flyBeetleEntity4);
        particularObjectManager.addObject(flyBeetleEntity4.getObject());
        
        flyBeetleEntity5=  initFlyingBeetleEntity(128,736,768,288);
        graphicsEntityManager.addObject(flyBeetleEntity5);
        particularObjectManager.addObject(flyBeetleEntity5.getObject());
        
        flyBeetleEntity6=  initFlyingBeetleEntity(128,736,768,288);
        graphicsEntityManager.addObject(flyBeetleEntity6);
        particularObjectManager.addObject(flyBeetleEntity6.getObject());
        
        egg1=  initEggSplosionEntity(384,576);
        graphicsEntityManager.addObject(egg1);
        particularObjectManager.addObject(egg1.getObject());
        
        egg2=  initEggSplosionEntity(512,576);
        graphicsEntityManager.addObject(egg2);
        particularObjectManager.addObject(egg2.getObject());
        
        
        egg3=  initEggSplosionEntity(1088,576);
        graphicsEntityManager.addObject(egg3);
        particularObjectManager.addObject(egg3.getObject());
        
        
        egg4=  initEggSplosionEntity(1152,576);
        graphicsEntityManager.addObject(egg4);
        particularObjectManager.addObject(egg4.getObject());
        
        //ITEM
        itemmanager.addObject(new Apple(800,200));
        itemmanager.addObject(new Mushroom(800,300));
        itemmanager.addObject(new Pear(800,244));
        itemmanager.addObject(new Banana(800,500));
        itemmanager.addObject(new Apple(100,244));
        itemmanager.addObject(new Strawberry(900,244));
        itemmanager.addObject(new Gold(896,992));
        itemmanager.addObject(new Gold(128,992));
        itemmanager.addObject(new Gold(1088,320));
        itemmanager.addObject(new Gold(32,416));
    }
    
    
    
    
    
    public void initEnemyMap2(){
        swordSkeEntity1=  initSwordSkeletonEntity(200,198,340,60);
        graphicsEntityManager.addObject(swordSkeEntity1);
        particularObjectManager.addObject(swordSkeEntity1.getObject());
        
        swordSkeEntity2=  initSwordSkeletonEntity(1664,576,320,320);
        graphicsEntityManager.addObject(swordSkeEntity2);
        particularObjectManager.addObject(swordSkeEntity2.getObject());
        
        swordSkeEntity3=  initSwordSkeletonEntity(832,768,288,128);
        graphicsEntityManager.addObject(swordSkeEntity3);
        particularObjectManager.addObject(swordSkeEntity3.getObject());
        
        swordSkeEntity4=  initSwordSkeletonEntity(832,768,288,128);
        graphicsEntityManager.addObject(swordSkeEntity4);
        particularObjectManager.addObject(swordSkeEntity4.getObject());
        
        swordSkeEntity5=  initSwordSkeletonEntity(1664,576,320,320);
        graphicsEntityManager.addObject(swordSkeEntity5);
        particularObjectManager.addObject(swordSkeEntity5.getObject());
        
        
        
        goblinEntity1=  initGoblinEntity(224,1060,704,30);
        graphicsEntityManager.addObject(goblinEntity1);
        particularObjectManager.addObject(goblinEntity1.getObject());
        
        goblinEntity2=  initGoblinEntity(1664,576,320,320);
        graphicsEntityManager.addObject(goblinEntity2);
        particularObjectManager.addObject(goblinEntity2.getObject());
        
        goblinEntity3=  initGoblinEntity(1664,576,320,320);
        graphicsEntityManager.addObject(goblinEntity3);
        particularObjectManager.addObject(goblinEntity3.getObject());
        
        //Item
        itemmanager.addObject(new Gold(384,672));
        itemmanager.addObject(new Gold(352,896));
        itemmanager.addObject(new Gold(1216,864));
        itemmanager.addObject(new Diamond(1920,256));
    }
    
    
    public MonsterEntity initSwordSkeletonEntity(int x, int y, int w,int h){
        SwordSkeleton swordSke=initSwordSkeleton(x,y,w,h);
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
    
    
    public MonsterEntity initGoblinEntity(int x, int y, int w,int h){
        Goblin goblin=initGoblin(x,y,w,h);
        MonsterEntity ssEntity= new MonsterEntity(goblin,this);
        
        ssEntity.runLeft = CacheDataLoader.getInstance().getAnimation("gob_walk_left");
        ssEntity.runRight = CacheDataLoader.getInstance().getAnimation("gob_walk_right");
        ssEntity.runUp = CacheDataLoader.getInstance().getAnimation("gob_walk_up");
        ssEntity.runDown = CacheDataLoader.getInstance().getAnimation("gob_walk_down");
          
        
        ssEntity.idleLeft = CacheDataLoader.getInstance().getAnimation("gob_idle_left");
        ssEntity.idleRight = CacheDataLoader.getInstance().getAnimation("gob_idle_right");
        ssEntity.idleUp = CacheDataLoader.getInstance().getAnimation("gob_idle_up");
        ssEntity.idleDown = CacheDataLoader.getInstance().getAnimation("gob_idle_down");
        
        ssEntity.attackLeft = CacheDataLoader.getInstance().getAnimation("gob_attack_left");
        ssEntity.attackRight = CacheDataLoader.getInstance().getAnimation("gob_attack_right");
        ssEntity.attackUp = CacheDataLoader.getInstance().getAnimation("gob_attack_up");
        ssEntity.attackDown = CacheDataLoader.getInstance().getAnimation("gob_attack_down");
        
        ssEntity.behurtLeft = CacheDataLoader.getInstance().getAnimation("gob_hurt_left");
        ssEntity.behurtRight = CacheDataLoader.getInstance().getAnimation("gob_hurt_right");
        ssEntity.behurtUp = CacheDataLoader.getInstance().getAnimation("gob_hurt_up");
        ssEntity.behurtDown = CacheDataLoader.getInstance().getAnimation("ske_hurt_down");
        
        ssEntity.deadLeft = CacheDataLoader.getInstance().getAnimation("gob_death_left");
        ssEntity.deadRight = CacheDataLoader.getInstance().getAnimation("gob_death_right");
        ssEntity.deadUp = CacheDataLoader.getInstance().getAnimation("gob_death_up");
        ssEntity.deadDown = CacheDataLoader.getInstance().getAnimation("gob_death_down");
        
        return ssEntity;
    }
    public MonsterEntity initFlyingBeetleEntity(int x, int y, int w,int h){
        FlyingBeetle flyBeetle=initFlyingBeetle(x,y,w,h);
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
    
    public MonsterEntity initEggSplosionEntity(int x, int y){
        EggSplosion eggE=initEggSplosion(x,y);
        MonsterEntity ssEntity= new MonsterEntity(eggE,this);
 
        ssEntity.idleLeft = CacheDataLoader.getInstance().getAnimation("eggsplosion_idle");
        ssEntity.idleRight = CacheDataLoader.getInstance().getAnimation("eggsplosion_idle");
        ssEntity.idleUp = CacheDataLoader.getInstance().getAnimation("eggsplosion_idle");
        ssEntity.idleDown = CacheDataLoader.getInstance().getAnimation("eggsplosion_idle");
        
        ssEntity.attackLeft = CacheDataLoader.getInstance().getAnimation("eggsplosion");
        ssEntity.attackRight = CacheDataLoader.getInstance().getAnimation("eggsplosion");
        ssEntity.attackUp = CacheDataLoader.getInstance().getAnimation("eggsplosion");
        ssEntity.attackDown = CacheDataLoader.getInstance().getAnimation("eggsplosion");
        return ssEntity;
    }
    
    
    public SwordSkeleton initSwordSkeleton(int x,int y, int w,int h){
        int randomX = (int)Math.floor(Math.random()*(w+1)+x);
        int randomY = (int)Math.floor(Math.random()*(h+1)+y);
        SwordSkeleton ske;
        ske= new SwordSkeleton(randomX,randomY,this);
        ske.setRange(x,y,w,h);
        ske.setDirection(ParticularObject.RIGHT_DIR);
        ske.run();
        return ske;
    }
    
    public Goblin initGoblin(int x,int y, int w,int h){
        int randomX = (int)Math.floor(Math.random()*(w+1)+x);
        int randomY = (int)Math.floor(Math.random()*(h+1)+y);
        Goblin gob;
        gob= new Goblin(randomX,randomY,this);
        gob.setRange(x,y,w,h);
        gob.setDirection(ParticularObject.RIGHT_DIR);
        gob.run();
        return gob;
    }
    public FlyingBeetle initFlyingBeetle(int x,int y, int w,int h){
        int randomX = (int)Math.floor(Math.random()*(w+1)+x);
        int randomY = (int)Math.floor(Math.random()*(h+1)+y);
        FlyingBeetle flyBeetle;
        flyBeetle= new FlyingBeetle(randomX,randomY,this);
        flyBeetle.setRange(x,y,w,h);
        flyBeetle.setDirection(ParticularObject.LEFT_DIR);
        flyBeetle.run();
        return flyBeetle;
    }
    
    public EggSplosion initEggSplosion(int x,int y){
        EggSplosion egg;
        egg= new EggSplosion(x,y,this);
        egg.setDirection(ParticularObject.LEFT_DIR);
        return egg;
    }
    
    
    
    
}

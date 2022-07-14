package com.hung.userinterface;

import com.hung.state.GameWorld;
import com.hung.gameobjects.Knight;
import java.awt.event.KeyEvent;

public class InputManger {
    
    public GamePanel gp;
    public GameWorld gameWorld;
    public Knight knight;
    
    public InputManger(GamePanel gp){
        this.gp=gp;
    }
    public InputManger(GameWorld gameWorld, GamePanel gp){
        this.gp=gp;
        this.gameWorld= gameWorld;
        this.knight=(Knight) gameWorld.knightEntity.getKnight();
    }
    
    
    public void processKeypressed(int keyCode) {
        //TITLE
        if(gp.gameState==gp.TITLE){
            switch(keyCode) {
		case KeyEvent.VK_UP:
                        gp.ui.commandNum--;
                        if(gp.ui.commandNum<0) gp.ui.commandNum=1;
			break;
		case KeyEvent.VK_DOWN:
                        gp.ui.commandNum++;
                        if(gp.ui.commandNum>1) gp.ui.commandNum=0;
			break;
		case KeyEvent.VK_LEFT:
                        break;
		case KeyEvent.VK_RIGHT:
                        break;
		case KeyEvent.VK_ENTER:
                        if(gp.ui.commandNum==0) {
                            gp.initGame();
                            gp.gameState=gp.PLAY;
                        }
                        if(gp.ui.commandNum==1) System.exit(0);
			break;
		
               
		}
            return;
        
        }
        
        if(gp.gameState==gp.GAMEOVER){
            if(keyCode==KeyEvent.VK_ENTER) {
                gp.gameWorld=null;
		gp.gameState=gp.TITLE;
            }
            return;
        
        }
        
        if(gp.gameState==gp.GAMEWIN){
            if(keyCode==KeyEvent.VK_ENTER) {
                gp.gameWorld=null;
                gp.gameState=gp.TITLE;
            }
            return;
        
        }
        
        
        
        
        
        //PLAY
        if(gp.gameState==gp.PLAY){
        
		switch(keyCode) {
		case KeyEvent.VK_UP:
                    if(gameWorld.isInventOpen){
                        
                        if(gameWorld.inventManager.slotRow!=0)gameWorld.inventManager.slotRow--;
                        
                    }else 
                        if(gameWorld.isShop){
                        if(gameWorld.shopManager.slotRow!=0) gameWorld.shopManager.slotRow--;
                        }
                    else
                        if (!knight.isAttacking){
                        knight.setDirection(Knight.UP_DIR);
			knight.run();
                        
                    }
                      break;
		case KeyEvent.VK_DOWN:
                    if(gameWorld.isInventOpen){
                        if(gameWorld.inventManager.slotRow!=8) gameWorld.inventManager.slotRow++;
                        
                    }else 
                        if(gameWorld.isShop){
                        if(gameWorld.shopManager.slotRow!=8) gameWorld.shopManager.slotRow++;
                        }
                    else
                        if (!knight.isAttacking){
                        knight.setDirection(Knight.DOWN_DIR);
			knight.run();
                        
                    }
                       break;
		case KeyEvent.VK_LEFT:
                    if(gameWorld.isInventOpen){
                        if(gameWorld.inventManager.slotCol!=0) gameWorld.inventManager.slotCol--;
                        
                    }else 
                        if(gameWorld.isShop){
                        if(gameWorld.shopManager.slotCol!=0) gameWorld.shopManager.slotCol--;
                        }
                    else
                        if (!knight.isAttacking){
                        knight.setDirection(Knight.LEFT_DIR);
			knight.run();
                        
                    }
                        break;
		case KeyEvent.VK_RIGHT:
                    if(gameWorld.isInventOpen){
                        if(gameWorld.inventManager.slotCol!=10)gameWorld.inventManager.slotCol++;
                        
                    }else
                        if(gameWorld.isShop){
                        if(gameWorld.shopManager.slotCol!=10) gameWorld.shopManager.slotCol++;
                        }
                    else
                        if (!knight.isAttacking){
                        knight.setDirection(Knight.RIGHT_DIR);
			knight.run();
                        
                    }
			break;
		case KeyEvent.VK_ENTER:
                    if(gameWorld.isInventOpen && !gameWorld.inventManager.in.isEmpty()){
                        int itemIndex = gameWorld.inventManager.getItemIndexOnSlot();
                        if(itemIndex < knight.getInvent().items.size()){
                        System.out.println(knight.getInvent().items.get(itemIndex).getDes());
                         knight.getInvent().UseItem(itemIndex);
                        }
                    } else if(gameWorld.isShop){
                        int itemIndex = gameWorld.shopManager.getItemIndexOnSlot();
                        if(itemIndex < knight.shop.items.size()){
                        System.out.println(knight.getInvent().items.get(itemIndex).getDes());
                         knight.shop.Buy(itemIndex, knight.getInvent());
                        }
                    }                  
			break;
                case KeyEvent.VK_E:
                    if(gameWorld.isInventOpen && !gameWorld.inventManager.in.isEmpty()){
                        if(knight.getCraft().items.size() < 4){
                        int itemIndex = gameWorld.inventManager.getItemIndexOnSlot();
                        knight.getCraft().MoveTocraft(knight.getInvent(), itemIndex);
                        }
                        else{
                          knight.getCraft().CompleCraft(knight.getInvent());
                        }
                    }  
			break;
                case KeyEvent.VK_Q:
                    if(gameWorld.isInventOpen){
                        knight.getCraft().Undo(knight.getInvent());
                    }  
			break;
                case KeyEvent.VK_V:
                    if(gp.isInExchangeArea()){
                        System.out.println("Exchange");
                        if(gameWorld.isShop)
                        gameWorld.isShop=false;
                    else if(!gameWorld.isInventOpen) 
                        gameWorld.isShop=true;
                    }
                        break;
		case KeyEvent.VK_ESCAPE:
                        gp.gameState=gp.PAUSE;
                        knight.stopRun();
			break;
		case KeyEvent.VK_A:
                        knight.attack();
			break;
                case KeyEvent.VK_D:
                        knight.attack2();
			break;
                case KeyEvent.VK_I:
                    if(gameWorld.isInventOpen)
                        gameWorld.isInventOpen=false;
                    else 
                         if(!gameWorld.isShop) 
                        gameWorld.isInventOpen=true;
			break;
                case KeyEvent.VK_P:
                    if(gameWorld.isShop)
                        gameWorld.isShop=false;
                    else if(!gameWorld.isInventOpen) 
                        gameWorld.isShop=true;
			break;
		}
            return;
        }
        
        
        //PAUSE
        if(gp.gameState==gp.PAUSE){
            switch(keyCode) {
		case KeyEvent.VK_UP:
                        gp.ui.commandNum--;
                        if(gp.ui.commandNum<0) gp.ui.commandNum=2;
			break;
		case KeyEvent.VK_DOWN:
                        gp.ui.commandNum++;
                        if(gp.ui.commandNum>2) gp.ui.commandNum=0;
			break;
		case KeyEvent.VK_LEFT:
                        break;
		case KeyEvent.VK_RIGHT:
                        break;
		case KeyEvent.VK_ENTER:
                        if(gp.ui.commandNum==0) {
                            //gp.initGame();
                            gp.gameState=gp.PLAY;
                        }
                        if(gp.ui.commandNum==1) {
                            gp.initGame();
                            gp.gameState=gp.PLAY;
                        }
                        if(gp.ui.commandNum==2) System.exit(0);
			break;
		
               
		}
            return;
        
        }
        
        
        
}
	
    public void processKeyreleased(int keyCode) {
        if(gp.gameState==gp.PLAY){
		switch(keyCode) {
		case KeyEvent.VK_UP:
                        knight.setSpeedY(0);
			break;
		case KeyEvent.VK_DOWN:
                        knight.setSpeedY(0);
			break;
		case KeyEvent.VK_LEFT:
                        knight.setSpeedX(0);
			break;
		case KeyEvent.VK_RIGHT:
                        knight.setSpeedX(0);
			break;
		case KeyEvent.VK_ENTER:
			break;
		case KeyEvent.VK_SPACE:
                        
			break;
		case KeyEvent.VK_A:
//                        knight.stopAttack();
			break;
               
		}
	}
    }
}

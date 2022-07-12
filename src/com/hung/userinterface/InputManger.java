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
        
        
        //PLAY
        if(gp.gameState==gp.PLAY){
        
		switch(keyCode) {
		case KeyEvent.VK_UP:
                    if(gameWorld.isInventOpen){
                        
                        if(gameWorld.inventManager.slotRow!=0)gameWorld.inventManager.slotRow--;
                        
                    }else if (!knight.isAttacking){
                        knight.setDirection(Knight.UP_DIR);
			knight.run();
                        
                    }
                      break;
		case KeyEvent.VK_DOWN:
                    if(gameWorld.isInventOpen){
                        if(gameWorld.inventManager.slotRow!=8) gameWorld.inventManager.slotRow++;
                        
                    }else if (!knight.isAttacking){
                        knight.setDirection(Knight.DOWN_DIR);
			knight.run();
                        
                    }
                       break;
		case KeyEvent.VK_LEFT:
                    if(gameWorld.isInventOpen){
                        if(gameWorld.inventManager.slotCol!=0) gameWorld.inventManager.slotCol--;
                        
                    }else if (!knight.isAttacking){
                        knight.setDirection(Knight.LEFT_DIR);
			knight.run();
                        
                    }
                        break;
		case KeyEvent.VK_RIGHT:
                    if(gameWorld.isInventOpen){
                        if(gameWorld.inventManager.slotCol!=10)gameWorld.inventManager.slotCol++;
                        
                    }else if (!knight.isAttacking){
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
                    else gameWorld.isInventOpen=true;
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

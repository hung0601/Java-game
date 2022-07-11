package com.hung.userinterface;

import com.hung.state.GameWorld;
import com.hung.gameobjects.Knight;
import java.awt.event.KeyEvent;

public class InputManger {
    
    public GameWorld gameWorld;
    public Knight knight;
    
    public InputManger(GameWorld gameWorld){
        this.gameWorld= gameWorld;
        this.knight=(Knight) gameWorld.knightEntity.getKnight();
    }
    
    
    public void processKeypressed(int keyCode) {
		switch(keyCode) {
		case KeyEvent.VK_UP:
                    if(gameWorld.isInventOpen){
                        gameWorld.inventManager.slotRow--;
                        
                    }else if (!knight.isAttacking){
                        knight.setDirection(Knight.UP_DIR);
			knight.run();
                        
                    }
                      break;
		case KeyEvent.VK_DOWN:
                    if(gameWorld.isInventOpen){
                        gameWorld.inventManager.slotRow++;
                        
                    }else if (!knight.isAttacking){
                        knight.setDirection(Knight.DOWN_DIR);
			knight.run();
                        
                    }
                       break;
		case KeyEvent.VK_LEFT:
                    if(gameWorld.isInventOpen){
                        gameWorld.inventManager.slotCol--;
                        
                    }else if (!knight.isAttacking){
                        knight.setDirection(Knight.LEFT_DIR);
			knight.run();
                        
                    }
                        break;
		case KeyEvent.VK_RIGHT:
                    if(gameWorld.isInventOpen){
                        gameWorld.inventManager.slotCol++;
                        
                    }else if (!knight.isAttacking){
                        knight.setDirection(Knight.RIGHT_DIR);
			knight.run();
                        
                    }
			break;
		case KeyEvent.VK_ENTER:
                    if(gameWorld.isInventOpen){
                        int itemIndex = gameWorld.inventManager.getItemIndexOnSlot();
                        if(itemIndex < knight.getInvent().items.size()){
                        System.out.println(knight.getInvent().items.get(itemIndex).getDes());
                         knight.getInvent().UseItem(itemIndex);
                        }
                    }      
            
			break;
		case KeyEvent.VK_SPACE:
			break;
		case KeyEvent.VK_A:
                        knight.attack();
			break;
                case KeyEvent.VK_I:
                    if(gameWorld.isInventOpen)
                        gameWorld.isInventOpen=false;
                    else gameWorld.isInventOpen=true;
			break;
		}
	}
	
    public void processKeyreleased(int keyCode) {
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

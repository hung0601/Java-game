package com.hung.userinterface;

import com.hung.effect.Animation;
import com.hung.effect.CacheDataLoader;
import com.hung.effect.FrameImage;
import com.hung.state.GameWorld;
import com.hung.gameobjects.Knight;
import com.hung.gameobjects.PhysicalMap;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable, KeyListener{
    
    
    public final int TITLE=0;
    public final int PLAY=1;
    public final int PAUSE=2;
    public final int GAMEOVER=3;
    public final int GAMEWIN=4;
    
    public int gameState=TITLE;
    
        
	
	private Thread thread;
	private boolean isRunning;
	private InputManger inputManger;
        
        public final int tileSize=32;
        
        private BufferedImage bufImage;
        private Graphics2D  bufG2D;
        
        public GameWorld gameWorld;
        public GameWorld gameWorld2;
        
        public int worldtype =1;
        
        public  UI ui;
        
        
        
	
	public GamePanel() {
            
            inputManger=new InputManger(this);
                
            bufImage=new BufferedImage(GameFrame.SCREEN_WIDTH,GameFrame.SCREEN_HEIGHT,BufferedImage.TYPE_INT_ARGB);
            ui = new UI(this);
	}
        
        public void initGame(){
            worldtype =1;
            gameWorld= new GameWorld(PhysicalMap.MAP2);
            gameWorld2= new GameWorld(PhysicalMap.MAP1);
            inputManger=new InputManger(gameWorld,this);
        }
        
        public void  gotoGameWorld2(){
            worldtype=2;
           
            inputManger.gameWorld=gameWorld2;
            inputManger.knight= (Knight) gameWorld2.knightEntity.getKnight();
            gameWorld2.knightEntity.getKnight().setPosX(640);
            gameWorld2.knightEntity.getKnight().setPosY(224);
            gameWorld2.knightEntity.getKnight().setBlood(gameWorld.knightEntity.getKnight().getBlood());
            gameWorld2.knightEntity.getKnight().setDirection(gameWorld.knightEntity.getKnight().getDirection());
            gameWorld2.knightEntity.getKnight().setInvent(gameWorld.knightEntity.getKnight().getInvent());
            gameWorld2.inventManager.in=gameWorld.knightEntity.getKnight().getInvent();
        
        }
        
        public void  gotoGameWorld1(){
            worldtype=1;
            
            inputManger.gameWorld=gameWorld;
            inputManger.knight= (Knight) gameWorld.knightEntity.getKnight();
            gameWorld.knightEntity.getKnight().setPosX(1088);
            gameWorld.knightEntity.getKnight().setPosY(1152);
            gameWorld.knightEntity.getKnight().setBlood(gameWorld2.knightEntity.getKnight().getBlood());
            gameWorld.knightEntity.getKnight().setDirection(gameWorld2.knightEntity.getKnight().getDirection());
            
            gameWorld.knightEntity.getKnight().setInvent(gameWorld2.knightEntity.getKnight().getInvent());
            gameWorld.inventManager.in=gameWorld2.knightEntity.getKnight().getInvent();
        
        
        }
        
        
        public boolean haveReachWorld2Enntrance(){
            if(worldtype==1 && gameWorld.knightEntity.getKnight().getPosY()>1200) return true;
            else return false;
        
        }
        
        
        public boolean haveReachWorld1Enntrance(){
            if(worldtype==2 && gameWorld2.knightEntity.getKnight().getPosY()<128
                   && gameWorld2.knightEntity.getKnight().getPosX()<768  ) return true;
            else return false;
        
        }
        
	@Override
	public void paint(Graphics g) {
		g.drawImage(bufImage, 0, 0, this);
	}
	
        public void UpdateGame(){
            if(gameState==PLAY){
                if(haveReachWorld2Enntrance()) gotoGameWorld2();
                if(haveReachWorld1Enntrance()) gotoGameWorld1();
                if(worldtype==1) gameWorld.Update();
                else gameWorld2.Update();
            }
        }
        
        public void RenderGame(){
            if(bufImage==null)
                bufImage=new BufferedImage(GameFrame.SCREEN_WIDTH,GameFrame.SCREEN_HEIGHT,BufferedImage.TYPE_INT_ARGB);
            if(bufImage!=null){
            bufG2D= (Graphics2D) bufImage.getGraphics();
            }
            if(bufG2D!=null){
                //bufG2D.setColor(Color.white);
		//bufG2D.fillRect(0,0,GameFrame.SCREEN_WIDTH,GameFrame.SCREEN_HEIGHT);
                
                switch(gameState){
                    case PLAY: 
                        if(worldtype==1) gameWorld.Render(bufG2D);
                        else gameWorld2.Render(bufG2D);
                        break;
                    case PAUSE:
                        if(worldtype==1) gameWorld.Render(bufG2D);
                        else gameWorld2.Render(bufG2D);
                        ui.drawPauseGame(bufG2D);
                        
                        break;
                    case TITLE:
                        ui.drawTitle(bufG2D);
                        
                        break;
                    case GAMEOVER:
                        break;
                    case GAMEWIN:
                        break;
                }
                

            }
        }
        
	public void startGame() {
		if(thread == null) {
			isRunning= true;
			thread= new Thread(this);
			thread.run();
		}
	}

	@Override
	public void run() {
		long FPS=60;
		long period=1000*1000000/FPS;
		long beginTime;
		long sleepTime;
		long deltaTime;
		
		beginTime= System.nanoTime();
		while(isRunning) {
                        
                        UpdateGame();
                        RenderGame();
                        repaint();
			deltaTime= System.nanoTime()-beginTime;		
			sleepTime=period-deltaTime;
			try {
				if(sleepTime>0)
				Thread.sleep(sleepTime/1000000);
				else  Thread.sleep(period/2000000);
			} catch(InterruptedException ex) {}
			
			beginTime=System.nanoTime();
		}
		
		
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		inputManger.processKeypressed(e.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent e) {
		inputManger.processKeyreleased(e.getKeyCode());

	}

    private void drawTitle() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
	
	

}

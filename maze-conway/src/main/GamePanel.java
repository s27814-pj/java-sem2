package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;


public class GamePanel extends JPanel implements Runnable{

	final int slotSize =25; //25x25
	//final int wallSize =5; // 5x25 + 25x5
	final static int maxCol = 40;
	final static int maxRow = 30;
	//final int screenWidth = ((slotSize+wallSize) * maxCol) + wallSize;
	//final int screenHeight = ((slotSize+wallSize) * maxRow) + wallSize;
	final int screenWidth = slotSize * maxCol;
	final int screenHeight = slotSize * maxRow;
	
	int FPS = 15;
	
	MazeMap map = new MazeMap();
	Player player = new Player();
	
	public KeyHandler keyPress = new KeyHandler();
	Thread visualThread;
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		//this.setSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.BLUE);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyPress);
		this.setFocusable(true);	
		
		map.generateMap();
		
	}
	
	public void startVisualThread() {
		visualThread = new Thread(this);
		visualThread.start();
	}
	
	public void run() {
		
		double drawTime = 1000000000/FPS;
		double delta = 0;
		long prevTime = System.nanoTime();
		long currentTime;
		
		while(visualThread!=null) {
			//System.out.println("thread's loop");
			
			currentTime = System.nanoTime();
			
			delta = delta + ((currentTime-prevTime)/drawTime);
			
			prevTime = currentTime;
			
			if (delta >=1) {
				update();
				repaint();
				delta--;
				
			}
			
			

		}
	}
	
	
	void changeColor() {
		this.setBackground(Color.RED);
	}
	
	
	int x=4*slotSize;
	int y=4*slotSize;
	
	public void update()  {
		if (keyPress.southPressed==true) {
			y+=slotSize;
			
			if((map.getField(player.posX, player.posY+1))!=1) 	player.goSouth();
			

			keyPress.southPressed=false;
			//while(keyPress.southPressed==true) try {Thread.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}
		} else if (keyPress.northPressed==true) {
			y-=slotSize;
			if((map.getField(player.posX, player.posY-1))!=1) player.goNorth();
			keyPress.northPressed=false;
		} else if (keyPress.eastPressed==true) {
			x+=slotSize;
			if((map.getField(player.posX+1, player.posY))!=1) player.goEast();
			keyPress.eastPressed=false;
		} else if (keyPress.westPressed ==true) {
			x-=slotSize;
			if((map.getField(player.posX-1, player.posY))!=1) player.goWest();
			keyPress.westPressed =false;
		} else if (keyPress.nextGenPressed == true) {
			map.nextGeneration();
			keyPress.nextGenPressed=false;
		} else if (keyPress.bombStatus == 1) {
			//player.deployBomb();
			player.deployBomb(map);
			keyPress.bombStatus=2;
			//map.setExit();
		}else if (keyPress.bombStatus == 3) {
			player.detonateBomb(map);
			
			keyPress.bombStatus =0;
		}
		if (player.getPos().equals(map.getPos())) {
			System.out.println("EXIT" + player.getPos() + " / "+map.getPos());
			System.exit(0);
			
		}
		//System.out.println("EXIT" + player.getPos() + " / "+map.getPos());
		
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		

		
		Graphics2D g3 = (Graphics2D)g;
		
		g3.setColor(Color.BLACK);
		
		for (int i=0; i<GamePanel.maxCol; i++) {
			for (int k=0; k<GamePanel.maxRow; k++) {
				if (map.getField(i, k) == 1) {
					g3.setColor(Color.BLACK);
					g3.fillRect(i*slotSize, k*slotSize, slotSize, slotSize);
				}
				if (map.getField(i, k) == 0) {
					g3.setColor(Color.WHITE);
					g3.fillRect(i*slotSize, k*slotSize, slotSize, slotSize);
				}
				if (map.getField(i, k) == 2) {
					g3.setColor(Color.RED);
					g3.fillOval(i*slotSize+slotSize/4, k*slotSize+slotSize/4, slotSize/2, slotSize/2);
				}
				if (map.getField(i, k) == 3) {
					g3.setColor(Color.GRAY);
					g3.fillRect(i*slotSize, k*slotSize, slotSize, slotSize);
				}
				if (map.getField(i, k) == 4) {
					g3.setColor(Color.RED);
					g3.fillRect(i*slotSize, k*slotSize, slotSize, slotSize);
				}
			}
		}
		//g3.setColor(Color.RED);
		//g3.fillOval((slotSize*10)+slotSize/4, (slotSize*10)+slotSize/4, slotSize/2, slotSize/2);
		
		//g3.dispose();
		
		
		Graphics2D g2 = (Graphics2D)g;
		
		//g2.setColor(Color.GREEN);
		//g2.fillRect(x, y, slotSize, slotSize);
		
		g2.setColor(Color.GREEN);
		g2.fillRect(player.posX*slotSize, player.posY*slotSize, slotSize, slotSize);
		
		//g2.dispose();
	}
}

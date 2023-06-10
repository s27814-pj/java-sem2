package main;

import java.util.Random;
import java.util.Vector;

public class MazeMap {

	private int[][] map = new int[GamePanel.maxCol][GamePanel.maxRow];
	Vector<Integer> v = new Vector<Integer>(2);
	
	public MazeMap(){
		v.add(1);
		v.add(1);
		generateMap();
		
	}
	
	public void pisz() {
		System.out.println("pisz");
		
	}
	
	public void prepareMap() {
		
		for (int i=0; i<GamePanel.maxCol; i++) {
			for (int k=0; k<GamePanel.maxRow; k++) {
				map[i][k]=0;
			}
		}
		Random random = new Random();

		for (int i=0; i<GamePanel.maxCol/4; i++) {
			for (int k=0; k<GamePanel.maxRow/4; k++) {
				map[i][k]=random.nextInt(2);
			}
		}
		
		
	}
	
	public void setExit() {
		Random rand = new Random();
		
		int rand1=rand.nextInt(GamePanel.maxCol/4);
		int rand2 = rand.nextInt(GamePanel.maxRow/4);
		//System.out.println(rand1  + " | "+rand2);
		
		v.set(0, GamePanel.maxCol-2-(rand1));
		v.set(1, GamePanel.maxRow-2-(rand2));
		map[GamePanel.maxCol-2-(rand1)][GamePanel.maxRow-2-(rand2)]=4;
		//System.out.println(map[GamePanel.maxCol-2-(rand1)][GamePanel.maxRow-2-(rand2)]);
		//map[10][10]=4;
		//System.out.println("jeste");
		
	}
	
	
	public void generateMap() {
		
		prepareMap();

		//setExit();
		
		for (int i=0; i<GamePanel.maxCol*4; i++) 	nextGeneration();
		//ensureMap();
		if(ensureMap()) generateMap();
		else setExit();
		
	}
	
	private boolean ensureMap(){
		int counter=0; //prawy dolny
		for (int i=GamePanel.maxCol/8; i>=1; i--) {
			counter+=map[GamePanel.maxCol-i][GamePanel.maxRow-1];			
		}
		if (counter==0) return true;
		counter =0; //prawy gorny
		for (int i=GamePanel.maxCol/8; i>=1; i--) {
			counter+=map[GamePanel.maxCol-i][0];			
		}
		if (counter==0) return true;
		counter =0; //lewy dolny
		for (int i=GamePanel.maxCol/8; i>=1; i--) {
			counter+=map[i][GamePanel.maxRow-1];			
		}
		if (counter==0) return true;
		return false;
		
		
	}
	
	
	public void nextGeneration() {

		int[][] next = new int[GamePanel.maxCol][GamePanel.maxRow];
		for (int l=0; l<GamePanel.maxCol; l++) {
			for (int m=0; m<GamePanel.maxRow; m++) {
				
                int neighbours = 0;
                for (int i = -1; i <= 1; i++)
                    for (int j = -1; j <= 1; j++)
                      if ((l+i>=0 && l+i<GamePanel.maxCol) && (m+j>=0 && m+j<GamePanel.maxRow))
                        neighbours += map[l + i][m + j];
 
                neighbours -= map[l][m];
 

               // if ((map[l][m] == 1) && (neighbours < 2))
                if ((map[l][m] == 1) && (neighbours == 0))
                	next[l][m] = 0;
                
                else if ((map[l][m] == 1) && (neighbours > 5))
                	next[l][m] = 0;
 
                else if ((map[l][m] == 0) && (neighbours == 3))
                	next[l][m] = 1;
 
                else
                	next[l][m] = map[l][m];
				
			}
		}
		map=next;
		
	}
	
	public int getField(int col, int row) {
		if ((col>=0) && (col <GamePanel.maxCol) && (row >=0) && (row <GamePanel.maxRow)) return map[col][row];
		else return 1;
	}
	public void setField(int col, int row, int val) {
		if ((col>=0) && (col <GamePanel.maxCol) && (row >=0) && (row <GamePanel.maxRow)) map[col][row]=val;
	}
	
	
	public Vector<Integer> getPos(){

		return v;
	}
}

package main;

public class Bomb extends Entity {
	
	Bomb(int x, int y){
		posX=x;
		posY=y;
		System.out.println("Bomb deployed");
		
	}
	
	Bomb(int x, int y, MazeMap map){
		posX=x;
		posY=y;
		System.out.println("Bomb deployed");
		map.setField(x, y, 2);
		
	}
	

}

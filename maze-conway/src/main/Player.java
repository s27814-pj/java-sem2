package main;


public class Player extends Entity {
	
	Player(){
		posX=4;
		posY=4;
	}
	
	int lastObjectX, lastObjectY;
	
	public void deployBomb() {
		Bomb bomb = new Bomb (posX, posY);
		
	}
	public void detonateBomb(MazeMap map) {
		System.out.println("BOOM");
	//map.getField(bomb.posX, bomb.posY)
		
		if (map.getField(lastObjectX-1, lastObjectY)==1) map.setField(lastObjectX-1, lastObjectY, 0);
		if (map.getField(lastObjectX+1, lastObjectY)==1)map.setField(lastObjectX+1, lastObjectY, 0);
		if (map.getField(lastObjectX, lastObjectY+1)==1)map.setField(lastObjectX, lastObjectY+1, 0);
		if (map.getField(lastObjectX, lastObjectY-1)==1)map.setField(lastObjectX, lastObjectY-1, 0);
		map.setField(lastObjectX, lastObjectY, 0);
		
	}
	
	
	public void deployBomb(MazeMap map) {
		Bomb bomb = new Bomb (posX, posY, map);
		lastObjectX=posX;
		lastObjectY=posY;

		
	}

}

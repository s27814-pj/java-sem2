package main;

import java.util.Vector;

public abstract class Entity {
protected int posX; // pos w tablicy
protected int posY;

Vector<Integer> v = new Vector<Integer>(2);


	Entity(){
		posX =0;
		posY =0;
		v.add(posX);
		v.add(posY);
	}
	
	public void goSouth() {
		if (posY<(GamePanel.maxRow-1)) posY++;		
	}
	
	public void goNorth() {
		if (posY>0) posY--;		
	}
	
	public void goWest() {
		if (posX>0) posX--;		
	}
	
	public void goEast() {
		if (posX<(GamePanel.maxCol-1)) posX++;		
	}
	
	public Vector<Integer> getPos(){
		v.set(0, posX);
		v.set(1, posY);
		return v;
	}
}

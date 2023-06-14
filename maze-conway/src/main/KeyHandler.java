package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
	
	public boolean northPressed, southPressed, eastPressed, westPressed, nextGenPressed;
	int bombStatus;
	int southValue =0;

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		int keyCode = e.getKeyCode();
		
		if (keyCode == KeyEvent.VK_W) {
			northPressed=true;
		}
		if (keyCode == KeyEvent.VK_A) {
			westPressed=true;
		}
		if (keyCode == KeyEvent.VK_S) {
			southPressed=true;
			southValue =1;
		}
		if (keyCode == KeyEvent.VK_D) {
			eastPressed=true;
		}
		if (keyCode == KeyEvent.VK_R) {
			nextGenPressed=true;
		}
		if (keyCode == KeyEvent.VK_B) {
			if (bombStatus == 0) bombStatus =1;
		}
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
		
		if (keyCode == KeyEvent.VK_W) {
			northPressed=false;
		}
		if (keyCode == KeyEvent.VK_A) {
			westPressed=false;
		}
		if (keyCode == KeyEvent.VK_S) {
			southPressed=false;
		}
		if (keyCode == KeyEvent.VK_D) {
			eastPressed=false;
		}
		if (keyCode == KeyEvent.VK_R) {
			nextGenPressed=false;
		}
		if (keyCode == KeyEvent.VK_B) {
			bombStatus =3;
		}
	}

}

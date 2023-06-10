package main;

import javax.swing.*;
import java.awt.*;


public class Main {

	public static void main(String[] args) {
		
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("MAZE");
		
		//JButton north = new JButton("NNNNN");
		//north.setPreferredSize(new Dimension(50, 50));
		//north.setBounds(400, 200, 50, 50);
		
		//JButton north2 = new JButton("EEEEE");
		//north.setPreferredSize(new Dimension(50, 50));
		//north2.setBounds(400, 200, 50, 50);
		

		GamePanel gamePanel = new GamePanel();
		
		//window.add(north, BorderLayout.NORTH);
		//window.add(north2, BorderLayout.SOUTH);
		window.add(gamePanel);
		window.pack();
		//window.setSize(1000,1000);
		//window.setLayout(new BorderLayout());
	
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		gamePanel.startVisualThread();

		//while (true) System.out.println("tutaj33333");

	}

}

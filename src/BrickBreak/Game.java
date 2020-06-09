package BrickBreak;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

public class Game extends JPanel implements KeyListener, ActionListener {

	// Constructor
	public Game() {
		
		
	}
	
	
	
	// Handles drawing features to the game window
	public void paint(Graphics g) {
	

		g.dispose();
	}
	
	
	// Implemented from interfaces
	// actionPerformed controls the game itself
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	// keyPressed handles key detection
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	// Functions must be implemented due to interface
	// However, not needed for the game
	public void keyTyped(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {}
	
}

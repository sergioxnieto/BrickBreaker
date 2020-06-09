package BrickBreak;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Timer;
import javax.swing.JPanel;

public class Game extends JPanel implements KeyListener, ActionListener {

	private boolean play = false;
	private int score = 0;
	private Timer timer;
	private int delay = 8;
	
	// Constructor
	public Game() {
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();
	}
	
	
	// Intermediate instantiations until factory is setup
	Paddle player = new Paddle();
	
	// Handles drawing features to the game window
	public void paint(Graphics g) {
		// background
		g.setColor(Color.black);
		g.fillRect(0, 0, 700, 600);
		
		// paddle
		player.paint(g);

		g.dispose();
	}
	
	
	// Implemented from interfaces
	// actionPerformed controls the game itself
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		
		// Update the canvas
		repaint();
	}
	
	// keyPressed handles key detection
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if(player.getX() >= 600) {
				player.setX(600);
			}
			else {
				play = true;
				player.moveRight();
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			if(player.getX() <= 0) {
				player.setX(0);
			}
			else {
				play = true;
				player.moveLeft();
			}
		}
		
		// Eventually setup a key to restart
		
	}
	// Functions must be implemented due to interface
	// However, not needed for the game
	public void keyTyped(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {}
	
}

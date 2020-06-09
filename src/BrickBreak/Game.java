package BrickBreak;

import java.awt.Color;
import java.awt.Font;
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
	private int totalBricks = 28;
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
	Ball ball = new Ball();
	Brick brick1 = new Brick();
	
	// Handles drawing features to the game window
	public void paint(Graphics g) {
		// display background
		g.setColor(Color.black);
		g.fillRect(0, 0, 700, 600);
		
		// display paddle
		player.paint(g);
		
		// display ball
		ball.paint(g);
		
		// display brick(s)
		if(!brick1.isDead())
			brick1.paint(g);
		
		// display score
		g.setColor(Color.white);
		g.setFont(new Font("serif", Font.BOLD, 25));
		g.drawString(""+score, 590, 30);
		
		// display victory screen and stop ball/game
		/*
		if(bricks empty) {
			play = false;
			ball.setXdir(0);
			ball.setYdir(0);
			g.setColor(Color.red);
			g.setFont(new Font("serif", Font.BOLD, 30));
			g.drawString("You Won! Score: "+score, 220, 300);
			g.setFont(new Font("serif", Font.BOLD, 20));
			g.drawString("Press Enter to Restart", 260, 350);
		}
		*/
		
		// display gameover and stop ball/game
		if(ball.getY() > 570) {
			play = false;
			ball.setXdir(0);
			ball.setYdir(0);
			g.setColor(Color.red);
			g.setFont(new Font("serif", Font.BOLD, 30));
			g.drawString("Game Over! Score: "+score, 220, 300);
			g.setFont(new Font("serif", Font.BOLD, 20));
			g.drawString("Press Enter to Restart", 260, 350);
		}

		g.dispose();
	}
	
	
	// Implemented from interfaces
	// actionPerformed controls the game itself
	public void actionPerformed(ActionEvent e) {
		if(play) {
			// Ball Movement across screen
			// Detects a collision between the paddle and ball
			if(ball.generateHitbox().intersects(player.generateHitbox())) {
				ball.setYdir(-(ball.getYdir()));
			}
		
			// Detect collision between ball and bricks
			// Currently handles one brick need to update
			// to handle more than one brick
			if(ball.generateHitbox().intersects(brick1.generateHitbox())) {
				// first check if the brick is alive
				if(!brick1.isDead()) {
					// kill normal brick // -1 hp to super brick
					brick1.setHP(brick1.getHP() - 1);
				
					// ball redirection
					// if ballRS hits brickLS or ballLS hits brickRS
					if((ball.getX() + 19 <= brick1.getX()) || (ball.getX() >= brick1.getX() + brick1.getWidth())) {
						ball.setXdir(-(ball.getXdir()));
					}
					else {
						ball.setYdir(-(ball.getYdir()));
					}
					
				}
				
			}
			
			// Changes the trajectory if it hits the borders
			ball.setX(ball.getX() + ball.getXdir());
			ball.setY(ball.getY() + ball.getYdir());
			if(ball.getX() <= 0) {
				ball.setXdir(-(ball.getXdir()));
			}
			if(ball.getY() < 0) {
				ball.setYdir(-(ball.getYdir()));
			}
			if(ball.getX() > 680) {
				ball.setXdir(-(ball.getXdir()));
			}
			
		}
		
		
		// Update the canvas
		repaint();
	}
	
	// keyPressed handles key detection
	// doesn't allow the paddle to travel past window borders
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if(player.getX() >= 600) {
				player.setX(600);
			}
			else {
				// if the game hasn't started and player moves right
				// ball moves to the right
				if(!play) {
					ball.setXdir(1);
				}
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
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			if(!play) {
				player = new Paddle();
				ball = new Ball();
				// need to prepare this for more than 1 brick
				brick1 = new Brick();
				score = 0;
				
				repaint();
			}
		}
		
	}
	// Functions must be implemented due to interface
	// However, not needed for the game
	public void keyTyped(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {}
	
}

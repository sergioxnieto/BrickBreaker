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
	private boolean firstRun = true;
	private boolean newColor = false;
	
	private int score = 0;
	private int rows = 3, cols = 7;
	private int totalBricks = rows * cols;
	
	private BricksGenerator bricks;
	
	private Timer timer;
	private int delay = 8;
	
	// Implementation of the Factory to generate background
	EntityFactory factory = new EntityFactory();
	Entity bg = factory.getEntity("BLACK");
	Entity cbg = factory.getEntity("BLUE");
	
	// Constructor
	public Game() {
		bricks = new BricksGenerator(rows, cols);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();
	}
	
	// Instantiations
	Paddle player = new Paddle();
	Ball ball = new Ball();
	
	// Handles drawing features to the game window
	public void paint(Graphics g) {
		// display background as black unless user chooses to switch
		bg.paint(g);
		if(newColor) {
			cbg.paint(g);
		}
		
		// draw bricks if they're still alive
		for(int i = 0; i < bricks.map.length; i++) {
			for(int j = 0; j < bricks.map[i].length; j++) {
				if(!(bricks.map[i][j].isDead())) {
					bricks.map[i][j].paint(g);
				}
				
			}
		}
		
		// display paddle
		player.paint(g);
		
		// display ball
		ball.paint(g);
		
		// display score
		g.setColor(Color.white);
		g.setFont(new Font("serif", Font.BOLD, 25));
		g.drawString(""+score, 590, 30);
		
		// display title screen
		if(firstRun) {
			play = false;
			g.setColor(Color.green);
			g.setFont(new Font("serif", Font.BOLD, 30));
			g.drawString("Brick Break", 270, 300);
			g.setFont(new Font("serif", Font.BOLD, 20));
			g.drawString("Press Left/Right Arrow to Begin", 220, 350);
			g.setFont(new Font("serif", Font.BOLD, 15));
			g.drawString("Press C key to change color", 220, 370);
		}
		
		// display victory screen and stop ball/game
		if(totalBricks == 0) {
			play = false;
			ball.setXdir(0);
			ball.setYdir(0);
			g.setColor(Color.red);
			g.setFont(new Font("serif", Font.BOLD, 30));
			g.drawString("You Won! Score: "+score, 220, 300);
			g.setFont(new Font("serif", Font.BOLD, 20));
			g.drawString("Press Enter to Restart", 260, 350);
		}
		
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
			A: for(int i = 0; i < bricks.map.length; i++) {
				for(int j = 0; j < bricks.map[i].length; j++) {
					Brick tmp = bricks.map[i][j];
					if(ball.generateHitbox().intersects(tmp.generateHitbox())) {
						// check to see if brick is alive
						if(!(tmp.isDead())) {
							// kill a living brick
							tmp.setHP(tmp.getHP() - 1);
							totalBricks--;
							score += 5;
							// ball redirection after collision
							if((ball.getX() + 19 <= tmp.getX()) || (ball.getX() >= tmp.getX() + tmp.getWidth())) {
								ball.setXdir(-(ball.getXdir()));
							}
							else {
								ball.setYdir(-(ball.getYdir()));
							}
							// exit to the outermost loop so that it
							// ensure to only register one hit at a time
							break A;
						}
						
					}
					
				}
			}
			
			// Changes ball trajectory if it hits the window borders
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
				firstRun = false;
				player.moveRight();
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			if(player.getX() <= 0) {
				player.setX(0);
			}
			else {
				play = true;
				firstRun = false;
				player.moveLeft();
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			if(!play) {
				player = new Paddle();
				ball = new Ball();
				score = 0;
				totalBricks = 21;
				bricks = new BricksGenerator(rows, cols);
				repaint();
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_C) {
			if(!play) {
				newColor = true;
			}
		}
		
	}
	// Functions must be implemented due to interface
	// However, not needed for the game
	public void keyTyped(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {}
	
}

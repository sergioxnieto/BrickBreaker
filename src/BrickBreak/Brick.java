package BrickBreak;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Brick implements Entity {
	
	private int x;
	private int y;
	private int width;
	private int height;
	private int hp;
	private boolean dead;
	
	// Default Constructor
	
	Brick() {
		this.x = 75;
		this.y = 50;
		this.width = 77;
		this.height = 50;
		this.hp = 1;
		this.dead = false;
	}
	
	// Formula for placing rectangles along x position
		
	// 80 is brick width + 3 so that there is a gap between them
	// (brickNum * 80) + 75
	
	// Formula for placing rectangles along x position
	// 60 is bright height + 10 so that there is a large gap
	// (brickNum * 60) + 50;
		
	// Total bricks will be 4 rows and 7 columns so 28 total 
	
	Brick(int mult) {
		this.x = 75;
		this.y = (mult*60) + 50;
		this.width = 77;
		this.height = 50;
		this.hp = 1;
		this.dead = false;
	}

	public int getX() {
		return this.x;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public void setHP(int hp) {
		this.hp = hp;
	}
	
	public int getHP() {
		return this.hp;
	}
	
	public boolean isDead() {
		if(this.hp == 0) {
			return this.dead = true;
		}
		else
			return this.dead = false;
	}
	
	@Override
	public void paint(Graphics g) {
		g.setColor(Color.white);
		g.setColor(Color.white);
		g.fillRect(this.x, this.y, this.width, this.height);
		
	}

	@Override
	public Rectangle generateHitbox() {
		Rectangle rect = new Rectangle(this.x, this.y, this.width, this.height);
		return rect;
	}

}

package BrickBreak;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Ball implements Entity {

	private int x;
	private int y;
	private int xdir;
	private int ydir;
	private int width;
	private int height;
	
	Ball() {
		this.x = 340;
		this.y = 529;
		this.xdir = -1;
		this.ydir = -2;
		this.width = 20;
		this.height = 20;
	}
	
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getXdir() {
		return xdir;
	}
	public void setXdir(int xdir) {
		this.xdir = xdir;
	}
	public int getYdir() {
		return ydir;
	}
	public void setYdir(int ydir) {
		this.ydir = ydir;
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(Color.yellow);
		g.fillOval(this.x, this.y, this.width, this.height);
	}

	@Override
	public Rectangle generateHitbox() {
		Rectangle rect = new Rectangle(this.x, this.y, this.width, this.height);
		return rect;
	}
	
}

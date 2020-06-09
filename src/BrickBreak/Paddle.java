import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Paddle implements Entity {

	
	private int x;
	private int y;
	private int width;
	private int height;
	
	Paddle() {
		this.x = 300;
		this.y = 550;
		this.width = 100;
		this.height = 8;
	}
	
	
	public void moveLeft() {
		this.x -= 20;
	}
	
	public void moveRight() {
		this.x += 20;
	}
	
	public int getX() {
		return this.x;
	}
	
	public void setX(int x) {
		this.x = x; 
	}
	
	@Override
	public void paint(Graphics g) {
		g.setColor(Color.green);
		g.fillRect(this.x, this.y, this.width, this.height);
		
	}

	@Override
	public Rectangle generateHitbox() {
		Rectangle rect = new Rectangle(this.x, this.y, this.width, this.height);
		return rect;
	}
	
	
}

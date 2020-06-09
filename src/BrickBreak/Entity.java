import java.awt.Graphics;
import java.awt.Rectangle;

public interface Entity {
	
	public void paint(Graphics g);
	public Rectangle generateHitbox();
	
}

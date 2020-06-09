import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Background implements Entity {
	
	Background() {}

	@Override
	public void paint(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, 700, 600);
	}

	@Override
	public Rectangle generateHitbox() {
		return null;
	}

}

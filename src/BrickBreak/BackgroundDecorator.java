package BrickBreak;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class BackgroundDecorator extends EntityDecorator {

	BackgroundDecorator(Entity entityToDecorate) {
		super(entityToDecorate);
	}

	
	// Decorates over the original background with the color blue
	
	@Override
	public void paint(Graphics g) {
		decoratedEntity.paint(g);
		g.setColor(Color.blue);
		g.fillRect(0, 0, 700, 600);
	}
	
	@Override
	public Rectangle generateHitbox() {
		// TODO Auto-generated method stub
		return null;
	}

}

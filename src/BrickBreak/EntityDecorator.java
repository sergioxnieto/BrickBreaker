package BrickBreak;

import java.awt.Graphics;

public abstract class EntityDecorator implements Entity {

	protected Entity decoratedEntity;
	
	EntityDecorator(Entity entityToDecorate) {
		this.decoratedEntity = entityToDecorate;
	}
	
	public void paint(Graphics g) {
		decoratedEntity.paint(g);
	}
}

package BrickBreak;

public class EntityFactory {
	public Entity getEntity(String entityType) {
		if(entityType == null) {
			return null;
		}
		
		
		if(entityType.equalsIgnoreCase("BLACK")){
	         return new Background();
	         
	    } else if(entityType.equalsIgnoreCase("BLUE")){
	    	
	    	// implementation of the decorator pattern:
	    	// takes in a background object and colors over it
	    	// with the color blue
	    	
	         return new BackgroundDecorator(new Background());
	         
	    }
	      
	    return null;
	}
}

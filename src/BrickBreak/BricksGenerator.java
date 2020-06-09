package BrickBreak;

public class BricksGenerator {
	
	public Brick map[][];
	
	public BricksGenerator(int rows, int columns) {
		map = new Brick[rows][columns];
		
		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map[i].length; j++) {
				map[i][j] = new Brick(j, i);
			}
		}
	}
	

}

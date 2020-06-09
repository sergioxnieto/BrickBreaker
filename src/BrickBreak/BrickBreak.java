import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class BrickBreak {

	public static void main(String[] args) {
		Dimension ss = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = new Dimension(700, 600);
		
		JFrame obj = new JFrame();
		Game game = new Game();
		obj.setBounds(ss.width/2 - frameSize.width/2, ss.height/2 - frameSize.height/2, frameSize.width, frameSize.height);
		obj.setTitle("Brick Break");
		obj.setResizable(false);
		obj.setVisible(true);
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		obj.add(game);

	}

}

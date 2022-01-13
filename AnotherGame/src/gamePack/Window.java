package gamePack;

import javax.swing.JFrame;

public class Window{
	private JFrame frame;
	public Window(AnotherGame game) {
		frame = new JFrame();
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setUndecorated(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.add(game);
		frame.setVisible(true);
		
	}
	
	public int getWidth() {
		return this.frame.getWidth();
	}
	public int getHeight() {
		return this.frame.getHeight();
	}
}

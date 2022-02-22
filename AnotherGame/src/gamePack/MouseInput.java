package gamePack;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingUtilities;

public class MouseInput extends MouseAdapter{
	public void mousePressed(MouseEvent e) {
		
		if(SwingUtilities.isLeftMouseButton(e)) {//left click
			if(!AnotherGame.handler.press(e.getX(), e.getY())) {//tries to click on a button, and if it fails , it will shoot
				AnotherGame.player.shoot(e.getX()+AnotherGame.cameraX,e.getY()+AnotherGame.cameraY);
			}
		}else {
			if(SwingUtilities.isRightMouseButton(e)) {//rightClick
				//target an ememy at the mouse location
				AnotherGame.player.target(AnotherGame.handler.getEnemyAt(e.getX(), e.getY()));
			}
		}
		

		
	}
	
	public void mouseReleased(MouseEvent e) {
		
	}
}



package gamePack;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{
	Player player;
	public KeyInput(Player player) {
		this.player = player;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_A) this.player.setLeft(true);
		if(key == KeyEvent.VK_D) this.player.setRight(true);
		if(key == KeyEvent.VK_W) this.player.setUp(true);
		if(key == KeyEvent.VK_S) this.player.setDown(true);
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_A) this.player.setLeft(false);
		if(key == KeyEvent.VK_D) this.player.setRight(false);
		if(key == KeyEvent.VK_W) this.player.setUp(false);
		if(key == KeyEvent.VK_S) this.player.setDown(false);
	}
}

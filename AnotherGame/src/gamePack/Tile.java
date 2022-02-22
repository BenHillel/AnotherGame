package gamePack;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile implements GameObject{
	public static final int size = 180;
	public static int width = (int)(size*(AnotherGame.WINDOW_WIDTH/(double)AnotherGame.GAME_WIDTH));
	public static int height = (int)(size*(AnotherGame.WINDOW_HEIGHT/(double)AnotherGame.GAME_HEIGHT));
	private int x,y;
	private TileType type;
	private BufferedImage image;
	public Tile(int x, int y, TileType type) {
		this.x = x;
		this.y = y;
		this.type = type;
		this.image = this.type.image;
	}
	
	
	@Override
	public void tick() {
		// TODO Auto-generated method stub
	}

	@Override
	public void render(Graphics g) {
		//g.setColor(this.color);
		//g.fillRect(this.x-AnotherGame.cameraX, this.y - AnotherGame.cameraY,width,height);
		if(this.onScreen()) {
			g.drawImage(image, x - AnotherGame.cameraX, y- AnotherGame.cameraY, width, height, null);
			
		}
		
	}
	
	
	public static void updateStuff() {
		width = (int)(size*(AnotherGame.WINDOW_WIDTH/(double)AnotherGame.GAME_WIDTH));
		height = (int)(size*(AnotherGame.WINDOW_HEIGHT/(double)AnotherGame.GAME_HEIGHT));
	}
	
	private boolean onScreen() {
		if(x > AnotherGame.cameraX-width && x < AnotherGame.cameraX + AnotherGame.WINDOW_WIDTH) {
			if(y > AnotherGame.cameraY-height && y < AnotherGame.cameraY + AnotherGame.WINDOW_HEIGHT) {
				return true;
			}
		}
		return false;
	}
}

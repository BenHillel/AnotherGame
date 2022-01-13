package gamePack;

import java.awt.Color;
import java.awt.Graphics;

public class Tile implements GameObject{
	public static final int size = 180;
	public static int width = (int)(size*(AnotherGame.WINDOW_WIDTH/(double)AnotherGame.GAME_WIDTH));
	public static int height = (int)(size*(AnotherGame.WINDOW_HEIGHT/(double)AnotherGame.GAME_HEIGHT));
	private int x,y;
	private Color color;
	private TileType type;
	public Tile(int x, int y, TileType type) {
		this.x = x;
		this.y = y;
		this.type = type;
		this.typeToColor();
	}
	
	
	@Override
	public void tick() {
		// TODO Auto-generated method stub
	}

	@Override
	public void render(Graphics g) {
		g.setColor(this.color);
		g.fillRect(this.x-AnotherGame.cameraX, this.y - AnotherGame.cameraY,width,height);
		
	}
	
	private void typeToColor() {
		if(this.type.equals(TileType.red)) {
			this.color = new Color(0,0,255);
		}
		if(this.type.equals(TileType.blue)) {
			this.color = new Color(50,50,255);
		}
		if(this.type.equals(TileType.green)) {
			this.color = new Color(75,75,255);
		}
	}
	
	public static void updateStuff() {
		width = (int)(size*(AnotherGame.WINDOW_WIDTH/(double)AnotherGame.GAME_WIDTH));
		height = (int)(size*(AnotherGame.WINDOW_HEIGHT/(double)AnotherGame.GAME_HEIGHT));
	}
}

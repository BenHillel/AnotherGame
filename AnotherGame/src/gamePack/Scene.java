package gamePack;

import java.awt.Graphics;
import java.util.Random;

public class Scene {
	private int width, height;
	public Tile[][] tileMap;
	private Random r;
	public Scene(int width,int height) {
		this.width = width;
		this.height = height;
		this.tileMap = new Tile[width][height];
		this.r = new Random();
		for(int i=0;i<this.width;i++) {
			for(int j=0;j<this.height;j++) {
				int t = r.nextInt(TileType.values().length);
				this.tileMap[i][j] = new Tile(i*Tile.width,j*Tile.height,this.intToType(t));
			}
		}
		;
	}
	

	public void tick() {
		// TODO Auto-generated method stub
		
	}
	
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		for(int i=0;i<this.width;i++) {
			for(int j=0;j<this.height;j++) {
				this.tileMap[i][j].render(g);
			}
		}
		
	}
	
	public int getWidth() {
		return this.width;
	}
	public int getHeight() {
		return this.height;
	}
	public TileType intToType(int i) {
		switch(i) {
		case 0:
			return TileType.red;
		case 1:
			return TileType.blue;
		default:
			return TileType.green;
		}
	}
	
	
}

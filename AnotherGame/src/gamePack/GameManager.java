package gamePack;

import java.awt.Graphics;

public class GameManager {
	public static int stage;
	public static Scene scene;
	public static int CLOCK;
	public static int WORLD_BORDER_X, WORLD_BORDER_Y;
	
	private static int MaxEnemiesStage1 = 20;
	
	public GameManager(int startStage) {
		stage = startStage;
		loadStage();
	}
	
	public void loadStage() {
		switch(stage) {
		case 0:
			scene = new Scene(50,50);
			WORLD_BORDER_X = 50*Tile.width;
			WORLD_BORDER_Y = 50*Tile.height;
		default:
			scene = new Scene(50,50);
			WORLD_BORDER_X = 50*Tile.width;
			WORLD_BORDER_Y = 50*Tile.height;
		}
	}
	
	public void unloadStage() {
		AnotherGame.handler.clearAll();
		stage++;
	}
	
	public void tick() {
		CLOCK++;
		if(CLOCK <= 0) CLOCK = 0;
		scene.tick();
		stage1();
	}
	
	public void render(Graphics g) {
		scene.render(g);
	}
	
	private void stage1() {
		if(AnotherGame.handler.numOfEnemies < MaxEnemiesStage1) {
			spawnEnemyInRandomLocation();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	private void spawnEnemyInRandomLocation() {
		int nx = AnotherGame.random.nextInt(WORLD_BORDER_X);
		int ny;
		if(nx >= AnotherGame.cameraX && nx <= AnotherGame.cameraX+AnotherGame.GAME_WIDTH) {
			if(AnotherGame.random.nextBoolean() && (AnotherGame.cameraY > AnotherGame.GAME_HEIGHT)) {
				ny = AnotherGame.random.nextInt(AnotherGame.cameraY+1);
			}else {
				ny = AnotherGame.random.nextInt(clamp(WORLD_BORDER_Y-AnotherGame.cameraY-AnotherGame.GAME_HEIGHT,1,WORLD_BORDER_Y))+AnotherGame.cameraY+AnotherGame.GAME_HEIGHT;
			}
		}else {
			ny = AnotherGame.random.nextInt(WORLD_BORDER_Y);
		}
		AnotherGame.handler.addGameObject(new BasicEnemyShip(nx,ny,10), 1);
	}
	
	private int clamp(int x,int min,int max) {
		if(x < min)return min;
		if(x > max) return max;
		return x;
	}
	
}

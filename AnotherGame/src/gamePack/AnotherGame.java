package gamePack;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class AnotherGame extends Canvas implements Runnable{
	private static final long serialVersionUID = 1L;
	public static final int GAME_WIDTH=1980,GAME_HEIGHT=1080;
	public static int cameraX = 0,cameraY = 0;
	Window window;
	private boolean running;
	public static int WINDOW_WIDTH,WINDOW_HEIGHT;
	
	public static Scene scene;
	
	Player player;
	public AnotherGame() {
		//parameters setup, pre start
		this.window = new Window(this);
		WINDOW_WIDTH = this.window.getWidth();
		WINDOW_HEIGHT = this.window.getHeight();
		Tile.updateStuff();
		player = new Player(10,10,100,100,Color.CYAN);
		this.addKeyListener(new KeyInput(player));
		
		scene = new Scene(50,50);
		scene.tileMap[0][0] = new Tile(0,0,TileType.blue);
		//start the game via the run loop -- the game engine
		this.running = true;
		this.run();
	}
	

	
	
	
	@Override
	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks =60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames =0;
		while(running) {
			long now = System.nanoTime();
			delta += (now-lastTime)/ns;
			lastTime = now;
			while(delta>=1) {
				tick();
				delta--;
			}
			render();
			frames++;
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
	}
	
	private void tick() {
		player.tick();
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0, 0,WINDOW_WIDTH, WINDOW_HEIGHT);
		
		scene.render(g);
		player.render(g);

		
		bs.show();
		g.dispose();
	}
	
	public static void main(String[] args) {
		new AnotherGame();
	}

}

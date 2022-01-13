package gamePack;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Entity implements GameObject{
	private int x;
	private int y;
	private int width;
	private int height;
	private Color color;
	private double velX,velY;
	public Entity(int x, int y , int width,int height,Color color) {
		this.x = x;
		this.y = y;
		this.width = (int)(width*(AnotherGame.WINDOW_WIDTH/(double)AnotherGame.GAME_WIDTH));
		this.height = (int)(height*(AnotherGame.WINDOW_HEIGHT/(double)AnotherGame.GAME_HEIGHT));
		this.color = color;
		this.setVelX(0);
		this.setVelY(0);
	}
	
	@Override 
	public void tick() {
		this.x += this.velX;
		this.y += this.velY;
	}
	
	@Override
	public void render(Graphics g) {
		g.setColor(this.getColor());
		g.fillRect(this.getX() - AnotherGame.cameraX, this.getY() - AnotherGame.cameraY, this.getWidth(), this.getHeight());
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public double getVelX() {
		return velX;
	}

	public void setVelX(double velX) {
		this.velX = velX;
	}

	public double getVelY() {
		return velY;
	}

	public void setVelY(double velY) {
		this.velY = velY;
	}



}

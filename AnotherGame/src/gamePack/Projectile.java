package gamePack;

public abstract class Projectile implements GameObject{
	private int x,y,width,height;
	private double velX,velY;
	private Status status;
	public Projectile(int x,int y,int width,int height,Status status) {
		this.x = x;
		this.y = y;
		this.setHeight(height);;
		this.setWidth(width);;
		this.setStatus(status);
	}
	
	@Override
	public void tick() {
		this.move();
	}
	
	public void move() {
		this.x += this.velX;
		this.y += this.velY;
	}
	
	public boolean onScreen() {
		if(x >= AnotherGame.cameraX && x < AnotherGame.cameraX + AnotherGame.WINDOW_WIDTH) {
			if(y >= AnotherGame.cameraY && y < AnotherGame.cameraY + AnotherGame.WINDOW_HEIGHT) {
				return true;
			}
		}
		return false;
	}
	
	public boolean collision(Entity e) {
		boolean xCollide = false;
		boolean yCollide = false;
		//collision on x
		if((this.x < e.getX() + e.getWidth() && this.x > e.getX()) || (e.getX() < this.x + this.width && e.getX() > this.x)) {
			xCollide = true;
		}
		//collision on y
		if((this.y < e.getY() + e.getHeight() && this.y > e.getY()) || (e.getY() < this.y + this.height && e.getY() > this.y)) {
			yCollide = true;
		}
		return xCollide && yCollide;
		
	}
	
	
	//=================================================
	//getters and setters =============================
	//=================================================
	
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
		this.width = (int)(width*(AnotherGame.WIDTH_FACTOR));
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = (int)(height*(AnotherGame.HEIGHT_FACTOR));
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
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	protected boolean allay;
	

}

package gamePack;

public abstract class Entity implements GameObject{
	private int x,width;
	private int y,height;
	private double velX,velY;
	private Status status;
	private int health;
	private int maxHealth;
	private boolean alive;
	public Entity(int x, int y,int width,int height,Status status) {
		this.x = x;
		this.y = y;
		this.setWidth(width);
		this.setHeight(height);
		this.setHealth(1);
		this.setMaxHealth(1);
		this.setVelX(0);
		this.setVelY(0);
		this.alive = true;
		this.status = status;
	}
	
	@Override 
	public void tick() {
		this.move();
	}
	
	public final void move() {
		this.x += this.velX;
		this.y += this.velY;

	}
	
	public boolean onScreen() {
		if(x >= AnotherGame.cameraX-200 && x < 200+AnotherGame.cameraX + AnotherGame.WINDOW_WIDTH) {
			if(y >= AnotherGame.cameraY-200 && y <200+ AnotherGame.cameraY + AnotherGame.WINDOW_HEIGHT) {
				return true;
			}
		}
		return false;
	}
	
	public void getHit(int damage,Entity shooter) {
		this.setHealth(this.getHealth() - damage);
	}
	
	public boolean collision(Entity e) {

		boolean xCollide = false;
		boolean yCollide = false;
		//collision on x
		if((this.x < e.x + e.width && this.x > e.x) || (e.x < this.x + this.width && e.x > this.x)) {
				xCollide = true;
		}
		//collision on y
		if((this.y < e.y + e.height && this.y > e.y) || (e.y < this.y + this.height && e.y > this.y)) {
			yCollide = true;
		}
		return xCollide && yCollide;


	}
	
	public boolean contains(int mx,int my) {
		if(mx >= this.x - AnotherGame.cameraX && mx <= x - AnotherGame.cameraX + width) {
			if(my >= this.y - AnotherGame.cameraY && my <= y - AnotherGame.cameraY + height) {
				return true;
			}
		}
		return false;
	}
	
	public void kill() {
		AnotherGame.handler.removeGameObject(this);
	}
	
	public double distanceFrom(int x,int y) {
		return Math.sqrt(Math.pow(this.x - x, 2) + Math.pow(this.y - y, 2));
	}
	
	public double distanceFrom(Entity e) {
		return distanceFrom(e.x,e.y);
	}
	
	public boolean isAlive() {
		return this.alive;
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
	
	public Status getStatus() {
		return this.status;
	}
	
	public void setStatus(Status status) {
		this.status = status;
	}
	
	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}


}

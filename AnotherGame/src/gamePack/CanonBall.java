package gamePack;

import java.awt.Color;
import java.awt.Graphics;

public class CanonBall extends Projectile{
	public static final Color CANON_BALL_COLOR = new Color(50,50,50);
	public static final int DISTANCE_TO_CHECK_COLLISIONS = 300;
	public static final double DEFAULT_SIZE = 50;
	private static final int DEFAULT_SPEED = 15;
	private int damage;
	private double size ;
	private int timeToLive; // 60 = 1 second
	private int speed;
	private Entity owner; 
	public CanonBall(int x, int y,int tx,int ty,double size,int speed,int damage,Entity owner) {
		super(x, y,(int)size,(int)size,owner.getStatus());
		this.size = size;
		this.timeToLive = 120;
		this.speed = speed;
		this.owner = owner;
		this.damage = damage;
		this.setDirection(tx, ty);
	}
	public CanonBall(int x, int y,int tx,int ty,int speed,int damage,Entity owner) {
		super(x, y,(int)DEFAULT_SIZE,(int)DEFAULT_SIZE,owner.getStatus());
		this.size = DEFAULT_SIZE;
		this.timeToLive = 120;
		this.speed = speed;
		this.owner = owner;
		this.damage = damage;
		this.setDirection(tx, ty);
	}
	public CanonBall(int x, int y,int tx,int ty,double size,int damage,Entity owner) {
		super(x, y,(int)size,(int)size,owner.getStatus());
		this.size = size;
		this.timeToLive = 120;
		this.speed = DEFAULT_SPEED;
		this.owner = owner;
		this.damage = damage;
		this.setDirection(tx, ty);
	}
	
	public CanonBall(int x, int y,int tx,int ty,int damage,Entity owner) {
		super(x, y,(int)DEFAULT_SIZE,(int)DEFAULT_SIZE,owner.getStatus());
		this.size = DEFAULT_SIZE;
		this.timeToLive = 120;
		this.speed = DEFAULT_SPEED;
		this.owner = owner;
		this.damage = damage;
		this.setDirection(tx, ty);
	}
	
	@Override 
	public void tick() {
		super.tick();
		this.timeToLive--;
		if(this.timeToLive <=0 ) {
			AnotherGame.handler.removeGameObject(this);
		}
		this.checkCollisions();
	}

	@Override
	public void render(Graphics g) {
		g.setColor(CANON_BALL_COLOR);
		g.fillOval(getX()-AnotherGame.cameraX, getY()-AnotherGame.cameraY, (int)(size*AnotherGame.WIDTH_FACTOR), (int)(size*AnotherGame.HEIGHT_FACTOR));
		
	}
	
	private void setDirection(int tx,int ty) {
		int distX = tx-this.getX();
		int distY = ty-this.getY();
		double dist = Math.sqrt(Math.pow(distX, 2)+Math.pow(distY, 2));
		this.setVelX((distX/dist)*this.speed);
		this.setVelY((distY/dist)*this.speed);
//		switch(dir) {
//		case up:
//			this.setVelY(-speed);
//			break;
//		case down:
//			this.setVelY(speed);
//			break;
//		case right:
//			this.setVelX(speed);
//			break;
//		case left:
//			this.setVelX(-speed);
//			break;
//		case upRight:
//			this.setVelY(-speed);
//			this.setVelX(speed);
//			break;
//		case upLeft:
//			this.setVelY(-speed);
//			this.setVelX(-speed);
//			break;
//		case downRight:
//			this.setVelY(speed);
//			this.setVelX(speed);
//			break;
//		case downLeft:
//			this.setVelY(speed);
//			this.setVelX(-speed);
//			break;
//		}
	}
	
	public Entity getOwner() {
		return this.owner;
	}
	
	private void checkCollisions() {
		for(Entity e : AnotherGame.handler.getObjectsCloseTo(this, DISTANCE_TO_CHECK_COLLISIONS)) {
			if(getStatus().equals(Status.ALLAY)) {
				if(e.getStatus().equals(Status.ENEMY) && e instanceof EnemyShip) {
					if(this.collision(e)) {
						e.getHit(damage,this.owner);
						AnotherGame.handler.removeGameObject(this);
						break;
					}
				}
			}else {
				if(e.getStatus().equals(Status.ALLAY) ) {
					if(this.collision(e)) {
						e.getHit(damage,this.owner);
						AnotherGame.handler.removeGameObject(this);
						
					}
				}
			}
		}
	}

}

package gamePack;

import java.awt.Color;
import java.awt.Graphics;

public abstract class EnemyShip extends Entity{
	public static final int DISTANCE_FROM_POSSESED = (int)(200*AnotherGame.HEIGHT_FACTOR);
	protected int numOfCrewMembers;
	protected int damage;
	protected boolean hostile;
	protected int health;
	public static int size = 100;
	private int timeToLive;
	private int speed;
	
	//possesed vars
	private int possesNum;
	private Entity target;
	

	public EnemyShip(int x, int y,int numOfCrewMembers,int damage) {
		super(x, y,size,size);
		this.numOfCrewMembers = numOfCrewMembers;
		this.damage = damage;
		this.hostile = false;
		this.allay = false;
		this.timeToLive = 1200;
		this.setTarget(null);
		// TODO Auto-generated constructor stub
	}
	
	public EnemyShip(EnemyShip ship) {//creating a possesd ship
		super(ship.getX(), ship.getY(),size,size);
		this.numOfCrewMembers = ship.numOfCrewMembers;
		this.damage = ship.damage;
		this.hostile = false;
		this.allay = true;
		this.timeToLive = 1200;
		this.possesNum = AnotherGame.player.getNumOfPossesed();
	}
	
	@Override 
	public void tick() {
		if(this.allay) {
			this.allyTick();
		}else {
			if(!this.hostile) {
				this.dormentTick();
				this.AFKTick();
			}else {
				this.hostileTick();
			}
		}
	}

	@Override
	public void render(Graphics g) {
		if(this.onScreen()) {
			if(this.allay) {
				g.setColor(Color.green);
			}else {
				if(this.hostile) {
					g.setColor(Color.red);
				}else {
					g.setColor(Color.yellow);
				}
			}

			
			g.fillRect(this.getX()-AnotherGame.cameraX, this.getY()-AnotherGame.cameraY,(int)(100*AnotherGame.WIDTH_FACTOR),(int)(100*AnotherGame.HEIGHT_FACTOR));
		}

		
	}
	
	public void getHit(int damage,Entity shooter) {
		this.health -= damage;
		this.setTarget(shooter);
		if(this.health <= 0) {
			if(!this.allay) {
				DeadShip deadShip = new DeadShip(this);
				AnotherGame.handler.addGameObject(deadShip, 1);
			}else {
				AnotherGame.player.removePossesed(this);
			}
			AnotherGame.handler.removeGameObject(this);
		}
	}
	
	protected void AFKTick() {
		if(this.timeToLive <=0 && !this.onScreen()) {
			AnotherGame.handler.removeGameObject(this);
		}else {
			this.timeToLive--;
		}
	}
	
	protected int getPossesNum() {
		return this.possesNum;
	}
	
	public void setPossesedNum(int n) {
		this.possesNum = n;
	}
	
	public abstract void attack();
	public abstract void dormentTick(); // ship behavior if its not hostile
	public abstract void hostileTick(); // ship behavior if its hostile
	public abstract void allyTick();
	public abstract EnemyShip createAllay(EnemyShip ship);

	public Entity getTarget() {
		return target;
	}

	public void setTarget(Entity target) {
		this.target = target;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
}

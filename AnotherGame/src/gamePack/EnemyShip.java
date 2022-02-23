package gamePack;

import java.awt.Color;
import java.awt.Graphics;

public abstract class EnemyShip extends Entity implements Activable{
	public static final int DISTANCE_FROM_POSSESED = (int)(200*AnotherGame.HEIGHT_FACTOR);
	public static final int BUTTON_WIDTH =(int)(200*(AnotherGame.WIDTH_FACTOR)),BUTTON_HEIGHT = (int)(100*(AnotherGame.HEIGHT_FACTOR)); 
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
	private Button possesButton,getSoulsButton;
	
	

	public EnemyShip(int x, int y,int numOfCrewMembers,int damage) {
		super(x, y,size,size,Status.ENEMY);
		this.numOfCrewMembers = numOfCrewMembers;
		this.damage = damage;
		this.hostile = false;
		this.timeToLive = 1200;
		this.setTarget(null);
		// TODO Auto-generated constructor stub
	}
	
	public EnemyShip(EnemyShip ship) {//creating a possesd ship
		super(ship.getX(), ship.getY(),size,size,Status.ALLAY);
		this.numOfCrewMembers = ship.numOfCrewMembers;
		this.damage = ship.damage;
		this.hostile = false;
		this.timeToLive = 1200;
		this.possesNum = AnotherGame.player.getNumOfPossesed();
	}
	
	@Override 
	public void tick() {
		switch(this.getStatus()) {
		case ALLAY:
			this.allyTick();
			break;
		case ENEMY:
			if(!this.hostile) {
				this.dormentTick();
				this.AFKTick();
			}else {
				this.hostileTick();
			}
			break;
		default:
			break;
		}
		
	}

	@Override
	public void render(Graphics g) {
		if(this.onScreen()) {
			switch(this.getStatus()) {
			case ENEMY:
				if(this.hostile) {
					g.setColor(Color.red);
				}else {
					g.setColor(Color.yellow);
				}
				break;
			case ALLAY:
				g.setColor(Color.green);
				break;
			case DEAD:
				g.setColor(Color.GRAY);
				break;
			default:
				g.setColor(Color.pink);
				break;
			}
			g.fillRect(this.getX()-AnotherGame.cameraX, this.getY()-AnotherGame.cameraY,(int)(100*AnotherGame.WIDTH_FACTOR),(int)(100*AnotherGame.HEIGHT_FACTOR));
		}

		
	}
	
	public void getHit(int damage,Entity shooter) {
		this.health -= damage;
		this.setTarget(shooter);
		if(this.health <= 0) {
			if(!this.getStatus().equals(Status.DEAD)) die();
		}
	}
	
	protected void AFKTick() {
		if(this.timeToLive <=0 && !this.onScreen()) {
			AnotherGame.handler.removeGameObject(this);
		}else {
			this.timeToLive--;
		}
	}
	
	protected void die() {
		switch(this.getStatus()) {
		case ENEMY:
			this.setStatus(Status.DEAD);
			//add buttons
			this.getSoulsButton = new Button(this.getX()-BUTTON_WIDTH*2,this.getY(),BUTTON_WIDTH,BUTTON_HEIGHT,1,true,this);
			this.possesButton= new Button(this.getX()+this.getWidth()+BUTTON_WIDTH,this.getY(),BUTTON_WIDTH,BUTTON_HEIGHT,2,true,this,"posses");
			AnotherGame.handler.addButton(getSoulsButton);
			AnotherGame.handler.addButton(possesButton);
			break;
		case DEAD:
			AnotherGame.handler.removeGameObject(this);
			break;
		case ALLAY:
			AnotherGame.player.removePossesed(this);
			break;
		default:
			AnotherGame.handler.removeGameObject(this);
		}
	}
	
	@Override 
	public void activate(int index) {
		this.removeButtons();
		switch(index) {
		case 1: // claim souls button pressed
			AnotherGame.player.souls+= this.numOfCrewMembers;
			die();
			break;
		case 2:// possess button pressed
			if(AnotherGame.player.getNumOfPossesed() == Player.maxPossesed) {
				AnotherGame.player.souls+= this.numOfCrewMembers;
				die();
			}else {
				setAllay();
				AnotherGame.player.addPossesed(this);
			}
			
			
		}
		
	}
	
	protected void setAllay() {
		this.setHealth(this.getMaxHealth());
		this.target = null;
		this.setStatus(Status.ALLAY);
		this.speed = 8;
	}
	
	public abstract void attack();
	public abstract void dormentTick(); // ship behavior if its not hostile
	public abstract void hostileTick(); // ship behavior if its hostile
	public abstract void allyTick();
	public abstract EnemyShip createAllay(EnemyShip ship);
	
	private void removeButtons() {
		AnotherGame.handler.removeButton(getSoulsButton);
		AnotherGame.handler.removeButton(possesButton);
	}
	
	protected void goToTarget() {
		int distX = getTarget().getX() - this.getX();
		int distY = getTarget().getY() - this.getY();
		double dist = this.distanceFrom(getTarget());
		this.setVelX((distX/dist)*getSpeed());
		this.setVelY((distY/dist)*getSpeed());
		this.move();
	}
	
	protected void goToPlayer() {
		int distX = AnotherGame.player.getX() - this.getX();
		int distY = AnotherGame.player.getY() - this.getY();
		double dist = this.distanceFrom(AnotherGame.player);
		this.setVelX((distX/dist)*getSpeed());
		this.setVelY((distY/dist)*getSpeed());
		this.move();
	}
	
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
	
	protected int getPossesNum() {
		return this.possesNum;
	}
	
	public void setPossesedNum(int n) {
		this.possesNum = n;
	}
	
}

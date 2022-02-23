package gamePack;

public class BasicEnemyShip extends EnemyShip{
	public static final int BASIC_ENEMY_DEMAGE = 1;
	public static final int CIRCLE_DISTANCE = AnotherGame.player.getWidth()*2;
	public int reloadTime = 120;
	private int attackTimer;
	
	public BasicEnemyShip(int x, int y, int numOfCrewMembers) {
		super(x, y, numOfCrewMembers, 1);
		setSpeed(3);
		this.setRandomDirection();
		this.health = 10;
		this.attackTimer = reloadTime;
	}
	
	public BasicEnemyShip(EnemyShip ship) {
		super(ship);
		setSpeed(8);
		this.health = 10;
		this.attackTimer = reloadTime;
	}


	
	@Override
	public void attack() {
		if(this.attackTimer <=0 ) {
			int tx = getTarget().getX(), ty = getTarget().getY();
			AnotherGame.handler.addGameObject(new CanonBall(this.getX()+getWidth()/2, this.getY()+getHeight()/2,tx,ty,BASIC_ENEMY_DEMAGE,this),1);
			this.attackTimer = this.reloadTime;
		}

	}
	@Override
	public void dormentTick() {
		this.move();
		this.attackTimer--;
	}
	
	@Override
	public void hostileTick() {
		if(getTarget() == AnotherGame.player) {
			if(this.distanceFrom(AnotherGame.player) <= CIRCLE_DISTANCE) {
				if(this.distanceFrom(AnotherGame.player) >= 2*CIRCLE_DISTANCE/3) {
					this.circleAroundPlayer();
					this.attack();
				}else {
					this.goAwayFromPlayer();
				}
				
			}else {
				this.goToPlayer();
			}
		}else {
			if(this.distanceFrom(getTarget())<= CIRCLE_DISTANCE) {
				this.attack();
			}else {
				this.goToTarget();
			}
		}

		this.attackTimer--;
		
	}
	
	@Override
	public void allyTick() {
		
		if(getTarget() != null) {
			if(this.getTarget().getHealth() > 0) {
				this.hostileTick();
			}else {
				setTarget(null);
			}
		}else {
			moveAfterPlayerFromDistance();
		}
		this.attackTimer--;
	}
	
	private void moveAfterPlayerFromDistance() {
		if(this.distanceFrom(AnotherGame.player) >= (this.getPossesNum()+1)*EnemyShip.DISTANCE_FROM_POSSESED) {
			goToPlayer();
		}
	}
	
	private void setRandomDirection() {
		double angle = AnotherGame.random.nextInt(360); // a random angle in degrees
		//angle = angle*(Math.PI/(double)180); // convert it to radians
		this.setVelX(getSpeed()*Math.cos(angle));
		this.setVelY(getSpeed()*Math.sin(angle));
		
	}

	public int getHealth() {
		return health;
	}

	@Override
	public void getHit(int damage,Entity shooter) {
		super.getHit(damage,shooter);
		this.hostile = true;
		if(!getStatus().equals(Status.ALLAY)) {
			setSpeed(5);
		}
		
	}
	

	
	private void circleAroundPlayer() {
		int distX = AnotherGame.player.getX() - this.getX();
		int distY = AnotherGame.player.getY() - this.getY();
		double dist = this.distanceFrom(AnotherGame.player);
		this.setVelX(-(distY/dist)*getSpeed());
		this.setVelY((distX/dist)*getSpeed());
		this.move();
	}
	
	private void goAwayFromPlayer() {
		int distX = AnotherGame.player.getX() - this.getX();
		int distY = AnotherGame.player.getY() - this.getY();
		double dist = this.distanceFrom(AnotherGame.player);
		this.setVelX(-(distX/dist)*getSpeed());
		this.setVelY(-(distY/dist)*getSpeed());
		this.move();
	}
	
	@Override
	public EnemyShip createAllay(EnemyShip ship) {
		EnemyShip dup = new BasicEnemyShip(ship);
		return dup;
	}
	
}

package gamePack;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class Player extends Entity{
	public static int maxAmmunition = 5;
	public static final String imageName = "player_ship";
	public static int maxPossesed = 2;
	public static EnemyShip targeted;
	
	public boolean[] directions;
	double playerSpeed;
	private int width,height;
	private BufferedImage image_right,image_left;
	private Direction drawingDirection;
	public HUD hud;
	public int maxHealth;
	private int ammunitionLeft;
	public int souls;
	private LinkedList<EnemyShip> possesed;
	
	
	//timers
	private static int RELOAD_TIME=120,SHOOT_COOLDOWN=10;
	private int reloadTime,shootCooldown;
	
	//Weapons damages 
	private static int cannonBallDamage = 3;
	
	public Player(int x, int y, int width, int height) {
		super(x, y,width,height);
		this.setWidth(width);
		this.setHeight(height);
		this.directions = new boolean[4];
		this.playerSpeed = 10;
		this.setHealth(20);
		this.image_right = AnotherGame.imageloader.getImage(imageName+"_right");
		this.image_left = AnotherGame.imageloader.getImage(imageName+"_left");
		this.drawingDirection = Direction.right;
		this.hud = new HUD(this);
		this.maxHealth = 20;
		this.souls = 0;
		this.possesed = new LinkedList<EnemyShip>();
		
		//weapon staff
		this.ammunitionLeft = maxAmmunition;
		
		//timers
		this.shootCooldown = 0;
		this.reloadTime = 0;
	}

	@Override
	public void tick() {		
		this.setX(this.getX()+(int)this.getVelX());
		this.setY(this.getY()+(int)this.getVelY());
		
		this.setMovement();
		
		//camera movement
		//if the camera is in the borders, set it to the player's position
		if(!this.isInCameraBorderX()) {
			AnotherGame.cameraX = this.getX()-AnotherGame.WINDOW_WIDTH/2 + this.getWidth()/2;
		}
		if(!this.isInCameraBorderY()) {
			AnotherGame.cameraY = this.getY()-AnotherGame.WINDOW_HEIGHT/2 + this.getHeight()/2; 
		}
		if(targeted != null) {
			if(targeted.health<=0) targeted = null;
		}
		hud.tick();
		timerTick();
	}
	
	@Override
	public void render(Graphics g) {
		switch(this.drawingDirection) {
		case right:
			g.drawImage(image_right, getX()- AnotherGame.cameraX, getY()- AnotherGame.cameraY, width, height, null);
			break;
		default:
			g.drawImage(image_left, getX()- AnotherGame.cameraX, getY()- AnotherGame.cameraY, width, height, null);
		}
		this.renderTargeted(g);
		hud.render(g);
	}
	
	public void shoot(int tx,int ty) {
		if(this.ammunitionLeft >0 && shootCooldown==0 && reloadTime==0) {
			AnotherGame.handler.addGameObject(new CanonBall(this.getX()+width/2, this.getY()+height/2,tx,ty,cannonBallDamage,this),1);
			shootCooldown = SHOOT_COOLDOWN;
			this.ammunitionLeft--;
			if(ammunitionLeft == 0) reload();
		}

		
		
	}
	
	public void reload() {
		this.ammunitionLeft = maxAmmunition;
		reloadTime = RELOAD_TIME;
	}
	
	@Override
	public void getHit(int damage,Entity shooter) {
		super.getHit(damage,shooter);
		if(this.getHealth() <= 0) {
			//death...
		}
	}
	
	private void timerTick() {
		this.shootCooldown = decrease(this.shootCooldown, 0);
		this.reloadTime = decrease(this.reloadTime,0);
		
	}
	
	private void renderTargeted(Graphics g) {
		if(targeted != null) {
			g.setColor(Color.PINK);
			g.fillOval(targeted.getX()-AnotherGame.cameraX,targeted.getY()-AnotherGame.cameraY, targeted.getWidth()/2, targeted.getHeight()/2);
		}

	}
	
	public void setRight(boolean val) {
		this.directions[0] = val;
	}
	public void setLeft(boolean val) {
		this.directions[1] = val;
	}
	public void setUp(boolean val) {
		this.directions[2] = val;
	}
	public void setDown(boolean val) {
		this.directions[3] = val;
	}
	
	private boolean isInCameraBorderX() {
		if(this.getX()<AnotherGame.WINDOW_WIDTH/2 - this.getWidth()/2) {
			return true;
		}
		if(this.getX() > GameManager.scene.getWidth()*Tile.width-AnotherGame.WINDOW_WIDTH/2 - this.getWidth()/2) {
			return true;
		}
		return false;
	}
	private boolean isInCameraBorderY() {
		if(this.getY()<AnotherGame.WINDOW_HEIGHT/2 - this.getHeight()/2) {
			return true;
		}
		if(this.getY() > GameManager.scene.getHeight()*Tile.height-AnotherGame.WINDOW_HEIGHT/2 - this.getHeight()/2) {
			return true;
		}
		return false;
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
	
	public Direction getDirection(){
		if(this.getVelY()<0) {//up
			if(this.getVelX()>0) return Direction.upRight;
			if(this.getVelX()<0) return Direction.upLeft;
			return Direction.up;
		}
		if(this.getVelY()>0) {//down
			if(this.getVelX()>0) return Direction.downRight;
			if(this.getVelX()<0) return Direction.downLeft;
			return Direction.down;
		}
		if(this.getVelX()>0) return Direction.right;
		if(this.getVelX()<0) return Direction.left;
		return Direction.up;
	}
	
	private void setMovement() {
		//horizontal movement
		if(this.directions[0]) {// Right is pressed
			if(!this.directions[1]) {
				this.setVelX(this.playerSpeed);
			}
		}else {
			if(this.directions[1]) {
				this.setVelX(-this.playerSpeed);
			}
		}
		//Vertical movement
		if(this.directions[2]) {// Right is pressed
			if(!this.directions[3]) {
				this.setVelY(-this.playerSpeed);
			}
		}else {
			if(this.directions[3]) {
				this.setVelY(this.playerSpeed);
			}
		}
		//None of the horizontal keys are pressed
		if(!(this.directions[0]||this.directions[1])) {
			this.setVelX(0);
		}		
		//None of the vertical keys are pressed
		if(!(this.directions[2]||this.directions[3])) {
			this.setVelY(0);
		}
		
		//if the player is moving, update the drawing direction
		if(directions[0] || directions[1] || directions[2] || directions[3] ) {
			this.setDrawingDirection();
		}
	}
	
	private void setDrawingDirection() {
		switch(this.getDirection()) {
		case right:
		case upRight:
		case downRight:
			this.drawingDirection = Direction.right;
			break;
		default:
			this.drawingDirection = Direction.left;
		}
	}
	
	public void addPossesed(EnemyShip ship) {
		if(possesed.size() < maxPossesed) {
			possesed.add(ship);
			ship.setPossesedNum(getNextPossesedDistanceAvalible());
		}
	}
	
	public void removePossesed(EnemyShip ship) {
		possesed.remove(ship);
	}
	
	public int getNumOfPossesed() {
		return possesed.size();
	}
	
	public void target(EnemyShip e) {
		if(e == null) return;
		if(!e.allay) {
			targeted = e;
			for(EnemyShip pos : this.possesed) {
				pos.setTarget(e);
			}
		}

	}
	
	public int getNextPossesedDistanceAvalible() {
		for(int i=1;i<=maxPossesed;i++) {
			boolean avalible = true;
			for(EnemyShip pos : this.possesed) {
				if(pos.getPossesNum() == i) avalible = false;
			}
			if(avalible) return i;
		}
		return maxPossesed;
	}
	
	public class HUD implements GameObject{
		public static final String imageName = "HUB";
		private final int CANON_BALL_WIDTH= (int)(CanonBall.DEFAULT_SIZE*AnotherGame.WIDTH_FACTOR),CANON_BALL_HEIGHT= (int)(CanonBall.DEFAULT_SIZE*AnotherGame.HEIGHT_FACTOR);
		private final int SOUL_WIDTH = (int)(100*AnotherGame.WIDTH_FACTOR),SOUL_HEIGHT = (int)(100*AnotherGame.HEIGHT_FACTOR);
		public Font font = new Font("Baskerville Old Face",1,40);
		public Color healthColor = new Color(50,255,100);
		private Player player;
		private int hWidth,hHeight;
		private int x,y,ammoX,ammoY,soulX,soulY;
		private BufferedImage backgroundImage,healthBarImage,cannonBallImage,soulImage;
		public HUD(Player player) {
			this.player = player;
			this.hHeight = AnotherGame.WINDOW_HEIGHT/8;
			this.hWidth = AnotherGame.WINDOW_WIDTH;
			this.x = 0;
			this.y = AnotherGame.GAME_HEIGHT-hHeight-300;
			//images
			this.backgroundImage = AnotherGame.imageloader.getImage(imageName+"_background");
			this.healthBarImage = AnotherGame.imageloader.getImage(imageName+"_healthBar");
			this.cannonBallImage = AnotherGame.imageloader.getImage("cannonBall");
			this.soulImage = AnotherGame.imageloader.getImage("soul");
			
			this.ammoX =  this.x+hWidth/2;
			this.ammoY = this.y+hHeight/2;
			this.soulX = this.x+9*hWidth/10;
			this.soulY = this.y;
		}
		@Override
		public void tick() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void render(Graphics g) {
			//background
			g.drawImage(this.backgroundImage,this.x,this.y-10, hWidth, hHeight,null);
			
			//health bar
			g.setColor(healthColor);
			g.fillRect(this.x+hWidth/5, this.y+5, (int)((2*hWidth/11)*(player.getHealth()/(double)player.maxHealth)), 2*hHeight/3);
			
			//health bar frame
			g.drawImage(healthBarImage,this.x+hWidth/20,this.y-10, hWidth/3, hHeight,null);
			
			//health number
			g.setColor(Color.BLACK);
			g.setFont(font);
			
			g.drawString(String.valueOf(player.getHealth()), x+6*hWidth/40, y+hHeight/2);
			
			//ammunition
			if(player.reloadTime>0) {//reloading
				g.drawString("reloading...", ammoX, ammoY);
			}else {
				g.drawString(String.valueOf(player.ammunitionLeft)+" x", ammoX + font.getSize()*4, ammoY);
				g.drawString("ammo = ", ammoX ,ammoY);
				g.drawImage(cannonBallImage, ammoX+font.getSize()*6, ammoY-CANON_BALL_HEIGHT, 3*CANON_BALL_WIDTH/2, 3*CANON_BALL_HEIGHT/2, null);
			}
			
			//soul counter
			g.drawString(String.valueOf(player.souls)+" x", soulX-2*SOUL_WIDTH/3, soulY+2*SOUL_HEIGHT/3);
			g.drawImage(soulImage,soulX, soulY, SOUL_WIDTH,SOUL_HEIGHT, null);
		}
		
	}
	
	private int decrease(int x,int bound) {
		if(x-1<bound) return x;
		return x-1;
	}

	
}

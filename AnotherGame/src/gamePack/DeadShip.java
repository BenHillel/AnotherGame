package gamePack;

import java.awt.Color;
import java.awt.Graphics;

public class DeadShip implements GameObject,Activable{
	public static final int BUTTON_WIDTH =(int)(200*(AnotherGame.WIDTH_FACTOR)),BUTTON_HEIGHT = (int)(100*(AnotherGame.HEIGHT_FACTOR)); 
	private int x,y,width,height,soulValue,damage;
	public Button b1,b2;
	private EnemyShip deadShip;
	public DeadShip(EnemyShip deadShip) {
		this.x = deadShip.getX();
		this.y = deadShip.getY();
		this.width =deadShip.getWidth();
		this.height = deadShip.getHeight();
		this.soulValue = deadShip.numOfCrewMembers;
		this.b1 = new Button(x-BUTTON_WIDTH*2,y,BUTTON_WIDTH,BUTTON_HEIGHT,1,true,this);
		this.b2 = new Button(x+deadShip.getWidth()+BUTTON_WIDTH,y,BUTTON_WIDTH,BUTTON_HEIGHT,2,true,this,"posses");
		this.addButtonsToHandler();
		this.setDamage(deadShip.damage);
		this.deadShip = deadShip;
	}
	
	@Override
	public void activate(int button) {
		if(button == 1) {
			claimSouls();
		}else {
			possesShip();
		}
	}
	
	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.gray);
		g.fillRect(x-AnotherGame.cameraX,y-AnotherGame.cameraY, width, height);
		
	}
	
	public void addButtonsToHandler() {
		AnotherGame.handler.addButton(b1);
		AnotherGame.handler.addButton(b2);
	}
	
	public void claimSouls() {
		AnotherGame.player.souls+= this.soulValue;
		selfDistract();
	}
	
	public void possesShip() {
		if(AnotherGame.player.getNumOfPossesed() == Player.maxPossesed) {
			claimSouls();
		}else {
			EnemyShip possesed = deadShip.createAllay(deadShip); 
			AnotherGame.player.addPossesed(possesed);
			AnotherGame.handler.addGameObject(possesed, 2);
			selfDistract();
		}
		
	}
	
	private void selfDistract() {
		AnotherGame.handler.removeGameObject(this);
		AnotherGame.handler.removeButton(b1);
		AnotherGame.handler.removeButton(b2);
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

	public int getSoulValue() {
		return soulValue;
	}

	public void setSoulValue(int soulValue) {
		this.soulValue = soulValue;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}
	


	
}

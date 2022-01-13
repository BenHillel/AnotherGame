package gamePack;

import java.awt.Color;

public class Player extends Entity{
	public boolean[] directions;
	double playerSpeed;
	public Player(int x, int y, int width, int height, Color color) {
		super(x, y, width, height, color);
		this.directions = new boolean[4];
		this.playerSpeed = 10;
	}

	@Override
	public void tick() {
		this.setX(this.getX()+(int)this.getVelX());
		this.setY(this.getY()+(int)this.getVelY());
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
}
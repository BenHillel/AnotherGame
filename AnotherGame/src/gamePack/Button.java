package gamePack;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Button implements GameObject{
	public static final String BUTTON_IMAGE_NAME = "BUTTON_";
	public int x,y,width,height,index;
	private boolean relative;
	private static Color defaultColor = Color.gray;
	private Activable parent;
	private BufferedImage image;
	public Button(int x,int y,int width,int height,int index,boolean relative,Activable parent,String imageName) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.relative = relative;
		this.parent = parent;
		this.index = index;
		this.image = AnotherGame.imageloader.getImage(BUTTON_IMAGE_NAME+imageName);
	}
	
	public Button(int x,int y,int width,int height,int index,boolean relative,Activable parent) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.relative = relative;
		this.parent = parent;
		this.index = index;
		this.image = AnotherGame.imageloader.getImage(BUTTON_IMAGE_NAME);
	}
	
	public boolean press(int mx,int my) {
		if(relative) {
			if(mx >= this.x - AnotherGame.cameraX && mx <= x - AnotherGame.cameraX + width) {
				if(my >= this.y - AnotherGame.cameraY && my <= y - AnotherGame.cameraY + height) {
					parent.activate(index);
					return true;
				}
			}
		}else {
			if(mx >= this.x  && mx <= x  + width) {
				if(my >= this.y  && my <= y + height) {
					parent.activate(index);
					return true;
				}
			}
		}
		return false;
	}
	

		
	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(defaultColor);
		if(relative) {
			if(onScreen()) {
				g.drawImage(image,x-AnotherGame.cameraX, y-AnotherGame.cameraY, width, height,null);
			}
		}else {
			g.drawImage(image,x, y, width, height,null);
		}
		// TODO Auto-generated method stub
		
	}
	
	public boolean onScreen() {
		if(x >= AnotherGame.cameraX-200 && x < 200+AnotherGame.cameraX + AnotherGame.WINDOW_WIDTH) {
			if(y >= AnotherGame.cameraY-200 && y <200+ AnotherGame.cameraY + AnotherGame.WINDOW_HEIGHT) {
				return true;
			}
		}
		return false;
	}
}

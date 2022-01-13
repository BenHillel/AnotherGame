package gamePack;

import java.awt.Graphics;

public interface GameObject {
	public abstract void tick();
	public abstract void render(Graphics g);
}

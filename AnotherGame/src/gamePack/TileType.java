package gamePack;

import java.awt.image.BufferedImage;

public enum TileType { 
	sea1("TILE_sea1"),sea2("TILE_sea2"),sea3("TILE_sea3");
	
	private TileType(String imageName) {
		this.image = AnotherGame.imageloader.getImage(imageName);
	}
	public final BufferedImage image;
}

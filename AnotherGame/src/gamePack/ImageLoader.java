package gamePack;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class ImageLoader {
	HashMap<String,BufferedImage> images = new HashMap<String,BufferedImage>();
	public static final String imagesFolderPath = "./source";
	public ImageLoader() {
		loadImages();
	}
	
	private BufferedImage loadImage(File file) {
		BufferedImage image = null;
		try {
			image = ImageIO.read(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("error in ImageLoader");
		}
		return image;
	}
	
	public BufferedImage getImage(String imageName) {
		if(images.containsKey(imageName)) {
			return images.get(imageName);
		}else {
			System.err.println("ERROR AT IMAGELOADER: image " + imageName+ " not found!");
			return null;
		}
	}
	
	private void loadImages() {
		
		File dir = new File(imagesFolderPath);
		for(File file : dir.listFiles()) {
			if(file.getName().endsWith(".png")) {
				images.put(file.getName().split(".png")[0], loadImage(file));
			}
		}
	}
	@SuppressWarnings("unused")
	private void printList(String[] ls) {
		System.out.print("[");
		for(String str : ls) {
			System.out.print(str + " , ");
		}
		System.out.println("]");
	}
	
}

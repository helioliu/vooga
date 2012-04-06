package sprites;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import levelEditor.Platfomer;

import com.golden.gamedev.object.Sprite;


public abstract class PlatformSprite extends Sprite {

	protected String path;
	protected double x;
	protected double y;
	
	public PlatformSprite() {
		super(null,0,0);
	}
	
	protected void setInitImage(String path) {
		File file= new File(path);
		BufferedImage myPic;
		try {
			myPic = ImageIO.read(file);
		    this.setImage(myPic);
				} catch (IOException e) {
			System.out.println("no image path");
		}
	}
	
	public abstract ArrayList<Object> writableObject();
	
	public abstract void parse(ArrayList<Object> o, Platfomer game);
}

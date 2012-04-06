package sprites;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import levelEditor.Platfomer;

import com.golden.gamedev.object.Sprite;


public class PlatformSprite extends Sprite {

	protected String path;
	protected double x;
	protected double y;
	protected Platfomer game;
	
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
	
	public ArrayList<Object> writableObject() {
		ArrayList<Object> o= new ArrayList<Object>();
		o.add(path);
		o.add(x);
		o.add(y);
		return o;
	}


	public void parse(ArrayList<Object> o, Platfomer myGame)  {
		game=myGame;
		path=(String) o.get(0);
		setInitImage(path);
		x= (Integer) o.get(1);
		y= (Integer) o.get(2);
	}
}

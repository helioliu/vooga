package sprites;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import levelEditor.*;
import game.*;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.CollisionRect;
import com.golden.gamedev.object.collision.CollisionShape;

public class Platform extends Sprite implements LevelEditable {

	String path;
	Platfomer game;
	
	public Platform() {
		super();
	}


	/**
	 * Default collision shape used in {@link #getDefaultCollisionShape()}, can
	 * be used in along with collision manager.
	 */
	protected CollisionShape defaultCollisionShape = null;

	/**
	 * Returns default {@linkplain #defaultCollisionShape collision shape}, can
	 * be used along with collision manager.
	 */
	public CollisionShape getDefaultCollisiosnShape() {
		if (this.defaultCollisionShape == null) {
			this.defaultCollisionShape = new CollisionRect();
		}
		this.defaultCollisionShape.setBounds(this.getX(), this.getY(),
				this.getWidth(), this.getHeight());
		return this.defaultCollisionShape;
	}


	public ArrayList<Object> writableObject() {
		ArrayList<Object> o= new ArrayList<Object>();
		o.add(path);
		o.add(getX());
		o.add(getY());
		return o;
	}


	public void parse(ArrayList<Object> o, Platfomer myGame)  {
		game=myGame;
		path=(String) o.get(0);
			try {	
		File file = new File(path);
	     	BufferedImage image;
			image = ImageIO.read(file);		
			setImage(image);
		} catch (IOException e) {
			e.printStackTrace();
		}

		setX( (Integer) o.get(1));
		setY( (Integer) o.get(2));
		game.PLATFORM.add(this);
	}


	public void setInitX(double d) {
		// TODO Auto-generated method stub
		
	}


	public void setInitY(double val) {
		// TODO Auto-generated method stub
		
	}


	public void setInitPath(String path) {
		// TODO Auto-generated method stub
		
	}

}
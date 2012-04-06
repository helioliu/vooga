package sprites;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import levelEditor.Platfomer;
import levelEditor.SpriteInfo;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.CollisionRect;
import com.golden.gamedev.object.collision.CollisionShape;

public class Platform extends PlatformSprite {

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
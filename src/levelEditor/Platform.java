package levelEditor;
import java.awt.image.BufferedImage;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.CollisionRect;
import com.golden.gamedev.object.collision.CollisionShape;

public class Platform extends Sprite {

	Platfomer game;
	
	public Platform(Platfomer p, BufferedImage image, SpriteInfo info) {
		super(image, info.getX(), info.getY());
		game=p;
	}

	public Platform(BufferedImage image) {
		super(image, 0, 0);
		game=null;
	}

	public Platform(double x, double y) {
		super(null, x, y);
		game=null;
	}

	 public Platform() {
         this(0, 0);
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

}
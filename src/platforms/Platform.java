package platforms;

import java.awt.image.BufferedImage;

import com.golden.gamedev.object.collision.CollisionRect;
import com.golden.gamedev.object.collision.CollisionShape;
import com.golden.gamedev.object.sprite.AdvanceSprite;

public class Platform extends AdvanceSprite {

	public Platform(BufferedImage[] image, double x, double y) {
		super(image, x, y);
	}

	public Platform(BufferedImage[] image) {
		this(image, 0, 0);
	}

	public Platform(double x, double y) {
		this(null, x, y);
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

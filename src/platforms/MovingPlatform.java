package platforms;

import java.awt.image.BufferedImage;

import com.golden.gamedev.object.collision.CollisionRect;
import com.golden.gamedev.object.collision.CollisionShape;

public class MovingPlatform extends Platform {

	double maxXCoordinate;
	double maxYCoordinate;
	double minXCoordinate;
	double minYCoordinate;
	
	public MovingPlatform(BufferedImage[] image, double x, double y) {
		super(image, x, y);
	}

	public MovingPlatform(BufferedImage[] image) {
		this(image, 0, 0);
	}

	public MovingPlatform(double x, double y) {
		this(null, x, y);
	}

	 public MovingPlatform() {
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
	@Override
	public CollisionShape getDefaultCollisiosnShape() {
		if (this.defaultCollisionShape == null) {
			this.defaultCollisionShape = new CollisionRect();
		}
		this.defaultCollisionShape.setBounds(this.getX(), this.getY(),
				this.getWidth(), this.getHeight());
		return this.defaultCollisionShape;
	}

}


package spriteCollisions;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.AdvanceCollisionGroup;

import core.EventManager;

public class SpritePlatformCollision extends AdvanceCollisionGroup {



	public SpritePlatformCollision() {
		super();
	}

//	public CollisionShape getCollisionShape1(Sprite s1) {
//		rect1.setBounds(s1.getX() + 6, s1.getY() + 12, s1.getWidth() - 12,
//				s1.getHeight() - 18);
//		return rect1;
//	}
//
//	public CollisionShape getCollisionShape2(Sprite s2) {
//		rect2.setBounds(s2.getX() + 6, s2.getY() - 4, s2.getWidth() - 12,
//				s2.getHeight() - 32);
//		return rect2;
//	}

	@Override
	public void collided(Sprite s1, Sprite s2) {
//		revertPosition1();
//		if (collisionSide == BOTTOM_TOP_COLLISION) {
//			s1.setVerticalSpeed(0);
//			((Character) s1).jumping = false; 
//		}
//		if (collisionSide == LEFT_RIGHT_COLLISION
//				|| collisionSide == RIGHT_LEFT_COLLISION) {
//			s1.setHorizontalSpeed(0);
//		}
		EventManager.getEventManager().sendEvent("floor collide");
	}
}
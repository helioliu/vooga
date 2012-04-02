package collisionType;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.AdvanceCollisionGroup;
import com.golden.gamedev.object.collision.CollisionShape;

public class SpritePlatformCollision extends AdvanceCollisionGroup {
	
	public SpritePlatformCollision() {
		super();
		pixelPerfectCollision = true;
	}

	@Override
	public CollisionShape getCollisionShape1(Sprite s1) {
		rect1.setBounds(s1.getX(), s1.getY(), s1.getWidth(), s1.getHeight());
		return rect1;
	}
	
	@Override
	public CollisionShape getCollisionShape2(Sprite s2) {
		rect2.setBounds(s2.getX(), s2.getY(), s2.getWidth(), s2.getHeight());
		return rect2;
	}
	
	@Override
	public void collided(Sprite s1, Sprite s2) {
		revertPosition1();
	    if (collisionSide == BOTTOM_TOP_COLLISION) {
	    	s1.setVerticalSpeed(0);
	    } 
	    if(collisionSide == LEFT_RIGHT_COLLISION ||
	    		collisionSide == RIGHT_LEFT_COLLISION){
	    	s1.setHorizontalSpeed(0);
	    }
	}
}


package core.conditions;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;

import core.Condition;

public class FloorCollision extends BasicCollisionGroup implements Condition {
	private boolean active;
	private boolean send;
	private boolean collided;
	
	public FloorCollision() {
		active = true;
	}

	@Override
	public void collided(Sprite arg0, Sprite arg1) {
		collided = true;
		if (active) {
			send = true;
			active = false;
		}
		if(!active) {
			send = false;
		}
	}

	@Override
	public boolean conditionTrue() {
		collided = false;
		this.checkCollision();
		if (!collided) {
			active = true;
		}
		
		
		return send;
	}

	@Override
	public void reset() {
		active = true;
	}

}

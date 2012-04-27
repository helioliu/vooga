package collisions;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;

import core.EventManager;

public class EventCollision extends BasicCollisionGroup {
	private String event;
	
	public EventCollision(boolean pixelPerfectCollision, String event) {
		this.event = event;
	}

	@Override
	public void collided(Sprite arg0, Sprite arg1) {
		EventManager.getEventManager().sendEvent(event);
	}

}

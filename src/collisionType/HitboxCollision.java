package collisionType;
import java.util.Arrays;

import sprites.Boxable;

import collisions.CollisionRect;
import collisions.CollisionShape;
import collisions.Hitbox;

import core.EventManager;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.AdvanceCollisionGroup;

public class HitboxCollision extends AdvanceCollisionGroup{

	//exactly one of the groups must be hitboxed 
	@Override
	public void collided(Sprite s1, Sprite s2) {
		Sprite hbs; //the sprite that is determined to be the one with hitboxes
		CollisionRect cr1; //the collisionshape of the sprite w/hitboxes
		CollisionRect cr2; //the collisionshape of the sprite w/o hitboxes
		if(Arrays.asList(s1.getClass().getInterfaces()).contains(Boxable.class)){
			hbs = s1;
			cr1 = new CollisionRect(rect1.x, rect1.y, rect1.getHeight(), rect1.getWidth());
			cr2 = new CollisionRect(rect2.x, rect2.y, rect2.getHeight(), rect2.getWidth());
		}else{
			hbs = s2;
			cr1 = new CollisionRect(rect2.x, rect2.y, rect2.getHeight(), rect2.getWidth());
			cr2 = new CollisionRect(rect1.x, rect1.y, rect1.getHeight(), rect1.getWidth());
		}
		
		boolean hitboxEvent = false;
		for(Hitbox h : ((Boxable)hbs).getHitboxes()){
			//cloning and shifting the hitbox so that it is relative to its respective sprite
			//as opposed to an absolute value
			CollisionShape shiftedHB = h.getShape().clone();
			shiftedHB.move(cr1.getX(), cr1.getY());
			if(cr2.intersects(shiftedHB)){
				//broadcast an event
				//the event is retrieved from h
				EventManager.getEventManager().sendEvent(h.getEvent());
				hitboxEvent = true;
			}
		}
		
		if(!hitboxEvent){
			//broadcast default collision behavior event
			//the event is retrieved from hbs
			EventManager.getEventManager().sendEvent(((Boxable)hbs).getDefaultEvent());
		}
		
		
	}

}

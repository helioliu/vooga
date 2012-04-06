package collisionType;
import java.util.Arrays;

import sprites.Boxable;

import collisions.CollisionRect;
import collisions.Hitbox;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.AdvanceCollisionGroup;

public class HitboxCollision extends AdvanceCollisionGroup{

	//exactly one of the groups must be hitboxed 
	@Override
	public void collided(Sprite s1, Sprite s2) {
		Sprite hbs, nhbs;
		CollisionRect cr;
		if(Arrays.asList(s1.getClass().getInterfaces()).contains(Boxable.class)){
			hbs = s1;
			nhbs = s2;
			cr = new CollisionRect(rect2.x, rect2.y, rect2.getHeight(), rect2.getWidth());
		}else{
			hbs = s2;
			nhbs = s1;
			cr = new CollisionRect(rect1.x, rect1.y, rect1.getHeight(), rect1.getWidth());
		}
		
		boolean hitboxEvent = false;
		for(Hitbox h : ((Boxable)hbs).getHitboxes()){
			if(cr.intersects(h.getShape())){
				//broadcast an event
				//the event is retrieved from h
				hitboxEvent = true;
			}
		}
		
		if(!hitboxEvent){
			//broadcast default collision behavior event
			//the event is retrieved from hbs
		}
		
		
	}

}

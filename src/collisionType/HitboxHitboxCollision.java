package collisionType;

import sprites.Boxable;

import collisions.CollisionRect;
import collisions.CollisionShape;
import collisions.Hitbox;

import core.EventManager;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.AdvanceCollisionGroup;

public class HitboxHitboxCollision extends AdvanceCollisionGroup{

	//both of the groups must be hitboxed 
	@Override
	public void collided(Sprite s1, Sprite s2) {
		//we're not using GTGE's collision shapes so we need to clone them, yo
		CollisionRect cr1 = new CollisionRect(rect1.x, rect1.y, rect1.getHeight(), rect1.getWidth());
		CollisionRect cr2 = new CollisionRect(rect2.x, rect2.y, rect2.getHeight(), rect2.getWidth());
		
		//hitbox-hitbox collisions
		for(Hitbox h1 : ((Boxable)s1).getHitboxes()){
			//clone and shift the hitbox so that it is relative to the game
			//instead of just its owner
			CollisionShape shapeshift1 = h1.getShape().clone();
			shapeshift1.move(cr1.getX(), cr1.getY());
			for(Hitbox h2 : ((Boxable)s2).getHitboxes()){
				CollisionShape shapeshift2 = h2.getShape().clone();
				shapeshift2.move(cr2.getX(), cr2.getY());
				//check all hitbox-hitbox collisions
				if(shapeshift1.intersects(shapeshift2)){
					EventManager.getEventManager().sendEvent(h1.getEvent());
					EventManager.getEventManager().sendEvent(h2.getEvent());
					System.out.println(h1.getEvent());
					System.out.println(h2.getEvent());
				}
			}
		}
		
		//sprite1 hitbox2 collisions
		for(Hitbox h2 : ((Boxable)s2).getHitboxes()){
			CollisionShape shapeshift = h2.getShape().clone();
			shapeshift.move(cr2.getX(), cr2.getY());
			if(shapeshift.intersects(cr1)){
				EventManager.getEventManager().sendEvent(h2.getEvent());
				System.out.println(h2.getEvent());
			}
		}
		
		//sprite2 hitbox1 collisions
		for(Hitbox h1 : ((Boxable)s1).getHitboxes()){
			CollisionShape shapeshift = h1.getShape().clone();
			shapeshift.move(cr1.getX(), cr1.getY());
			if(shapeshift.intersects(cr2)){
				EventManager.getEventManager().sendEvent(h1.getEvent());
				System.out.println(h1.getEvent());
			}
		}
		
		//sprite-sprite collisions
		EventManager.getEventManager().sendEvent(((Boxable)s2).getDefaultEvent());
		System.out.println(((Boxable)s2).getDefaultEvent());
		EventManager.getEventManager().sendEvent(((Boxable)s1).getDefaultEvent());
		System.out.println(((Boxable)s1).getDefaultEvent());
	}

}

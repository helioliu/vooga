package collisionType;

import sprites.Boxable;

import collisions.CollisionRect;
import collisions.CollisionShape;
import collisions.Hitbox;

import core.EventManager;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.AdvanceCollisionGroup;

/**
 * Defines a collision manager for a collision group that holds
 * two sprite groups that both contain sprites implements hitboxes
 */
public class HitboxHitboxCollision extends AdvanceCollisionGroup{

	/**
	 * Checks for hitbox-hitbox collisions, then hitbox-sprite collisions,
	 * then sprite-hitbox collisions, then sprite-sprite collisions.
	 * Collisions are broadcast in pairs for listening (this will be
	 * actually added once the event system is finalized)
	 * 
	 * Both sprites must implement Boxable and IDable
	 */
	public void collided(Sprite s1, Sprite s2) {
		//we're not using GTGE's collision shapes so we need to clone them, yo
		CollisionRect cr1 = new CollisionRect(rect1.x, rect1.y, rect1.getHeight(), rect1.getWidth());
		CollisionRect cr2 = new CollisionRect(rect2.x, rect2.y, rect2.getHeight(), rect2.getWidth());
		
		//hitbox-hitbox collisions
		//we're using these sorts of for loops in case we need to know
		//the index of the hitboxes
		for(int i=0; i < ((Boxable)s1).getHitboxes().size(); i++){
			Hitbox h1 = ((Boxable)s1).getHitboxes().get(i);
			//clone and shift the hitbox so that it is relative to the game
			//instead of just its owner
			CollisionShape shapeshift1 = h1.getShape().clone();
			shapeshift1.move(cr1.getX(), cr1.getY());
			for(int j=0; j < ((Boxable)s2).getHitboxes().size();j++){
				Hitbox h2 = ((Boxable)s2).getHitboxes().get(j);
				CollisionShape shapeshift2 = h2.getShape().clone();
				shapeshift2.move(cr2.getX(), cr2.getY());
				
				//check all hitbox-hitbox collisions
				if(shapeshift1.intersects(shapeshift2)){
					EventManager.getEventManager().sendEvent("collision "+s1.getID()+" "+h1.getID()
							+" "+s2.getID()+" "+h2.getID());
					EventManager.getEventManager().sendEvent("collision "+s2.getID()+" "+h2.getID()
							+" "+s1.getID()+" "+h1.getID());
					System.out.println("collision "+s1.getID()+" "+h1.getID()+" "+s2.getID()+" "+h2.getID());
					System.out.println("collision "+s2.getID()+" "+h2.getID()+" "+s1.getID()+" "+h1.getID());
				}
			}
		}
		
		//sprite1 hitbox2 collisions
		for(Hitbox h2 : ((Boxable)s2).getHitboxes()){
			CollisionShape shapeshift = h2.getShape().clone();
			shapeshift.move(cr2.getX(), cr2.getY());
			if(shapeshift.intersects(cr1)){
				//EventManager.getEventManager().sendEvent(h2.getEvent());
				EventManager.getEventManager().sendEvent("collision "+s1.getID()+" "+
						s2.getID()+" "+h2.getID());
				System.out.println("collision "+s1.getID()+" "+	s2.getID()+" "+h2.getID());
			}
		}
		
		
		//sprite2 hitbox1 collisions
		//for(Hitbox h1 : ((Boxable)s1).getHitboxes()){
		for(int i=0; i<((Boxable)s1).getHitboxes().size(); i++){
			Hitbox h1 = ((Boxable)s1).getHitboxes().get(i);
			CollisionShape shapeshift = h1.getShape().clone();
			shapeshift.move(cr1.getX(), cr1.getY());
			if(shapeshift.intersects(cr2)){
				EventManager.getEventManager().sendEvent("collision "+s2.getID()+" "+
						s1.getID()+" "+h1.getID());
				System.out.println("collision "+s2.getID()+" "+	s1.getID()+" "+h1.getID());
				}
		}
		
		//sprite-sprite collisions
		EventManager.getEventManager().sendEvent("collision "+s1.getID()+" "+s2.getID());
		System.out.println("collision "+s1.getID()+" "+s2.getID());
		EventManager.getEventManager().sendEvent("collision "+s2.getID()+" "+s1.getID());
		System.out.println("collision "+s2.getID()+" "+s1.getID());
	}

}

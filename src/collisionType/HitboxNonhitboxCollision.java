package collisionType;
import java.util.Arrays;

import sprites.Boxable;

import collisions.CollisionRect;
import collisions.CollisionShape;
import collisions.Hitbox;

import core.EventManager;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.AdvanceCollisionGroup;

/**
 * Defines a collision manager for a collisiongroup that holds
 * one sprite group with sprites with hitboxes and another sprite
 * group with sprites without hitboxes
 */
public class HitboxNonhitboxCollision extends AdvanceCollisionGroup{

	/**
	 * Checks for hitbox-sprite collisions and broadcasts
	 * appropriate events, then broadcasts the normal sprite-sprite
	 * collision. Collisions will be broadcast in pairs for
	 * listening (a col w/b AND b col w/a) once the event system
	 * is finalized
	 */
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
		
		//for(Hitbox h : ((Boxable)hbs).getHitboxes()){
		for(int i=0; i<((Boxable)hbs).getHitboxes().size(); i++){
			Hitbox h = ((Boxable)hbs).getHitboxes().get(i);
			//clone and shift the hitbox so that it is relative to the game
			//instead of just its owner
			CollisionShape shiftedHB = h.getShape().clone();
			shiftedHB.move(cr1.getX(), cr1.getY());
			if(cr2.intersects(shiftedHB)){
				//broadcast an event
				EventManager.getEventManager().sendEvent("collision "+s1.getID()+" "+s2.getID()+" "+h.getID());
				System.out.println("collision "+s1.getID()+" "+s2.getID()+" "+h.getID());
				EventManager.getEventManager().sendEvent("collision "+s2.getID()+" "+h.getID()+" "+s1.getID());
				System.out.println("collision "+s2.getID()+" "+h.getID()+" "+s1.getID());
			}
		}
		
		//broadcast default collision behavior event
		EventManager.getEventManager().sendEvent("collision "+s1.getID()+" "+s2.getID());
		System.out.println("collision "+s1.getID()+" "+s2.getID());
		EventManager.getEventManager().sendEvent("collision "+s2.getID()+" "+s1.getID());
		System.out.println("collision "+s2.getID()+" "+s1.getID());
	}

}

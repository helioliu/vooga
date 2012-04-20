package collisionType;

import java.util.Arrays;

import sprites.Boxable;
import collisions.CollisionRect;
import collisions.CollisionShape;
import collisions.Hitbox;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.AdvanceCollisionGroup;

import core.EventManager;

/**
 * Defines a collision manager for a collisiongroup that holds
 * one sprite group with sprites with hitboxes and another sprite
 * group with sprites without hitboxes
 */
public abstract class AbstractHitboxNonhitboxCollision extends ShapeCollision{
	
	/**
	 * Checks for hitbox-sprite collisions and broadcasts all appropriate events.
	 * Events are broadcast in pairs for listening (s1 collides with s2 AND s2
	 * collides with s1).
	 * Sprite s1 is from the first sprite group (the one with hitboxes)
	 * Sprite s2 is from the second sprite group (without hitboxes)
	 * Or at least, that <i>should</i> be the order, but the method checks just in case
	 */
	@Override
	public void collided(Sprite s1, Sprite s2) {
		Sprite hbs; //the sprite that is determined to be the one with hitboxes
		Sprite nhbs;
		initCollisionShapes();//cr1 and cr2
		if(Arrays.asList(s1.getClass().getInterfaces()).contains(Boxable.class)){
			hbs = s1;
			nhbs = s2;
		}else{
			hbs = s2;
			nhbs = s1;
			switchShapes();
		}
		
		for(int i=0; i<((Boxable)hbs).getHitboxes().size(); i++){
			Hitbox h = ((Boxable)hbs).getHitboxes().get(i);
			//clone and shift the hitbox so that it is relative to the game
			//instead of relative to its owner
			CollisionShape shiftedHB = h.getShape().clone();
			shiftedHB.move(cr1.getX(), cr1.getY());
			if(cr2.intersects(shiftedHB)){
				//broadcast an event
				EventManager.getEventManager().sendEvent("collision "+s1.getID()+" "+s2.getID()+" "+h.getID());
				  System.out.println("collision "+s1.getID()+" "+s2.getID()+" "+h.getID());
				EventManager.getEventManager().sendEvent("collision "+s2.getID()+" "+h.getID()+" "+s1.getID());
				  System.out.println("collision "+s2.getID()+" "+h.getID()+" "+s1.getID());
				//do stuff specific to the hitbox
				hitboxSpriteCollided(hbs, h, nhbs);
			}
		}
		
		//broadcast default collision behavior event
		EventManager.getEventManager().sendEvent("collision "+s1.getID()+" "+s2.getID());
		  System.out.println("collision "+s1.getID()+" "+s2.getID());
		EventManager.getEventManager().sendEvent("collision "+s2.getID()+" "+s1.getID());
		  System.out.println("collision "+s2.getID()+" "+s1.getID());
		//do stuff specific for the sprites
		spriteCollided(hbs, nhbs);
	}
	
	/**
	 * Defines additional behavior for collisions between the two sprites
	 * not handled by the collided method.  Collisions specifically involving
	 * hitboxes should be handled by spriteHitboxCollided
	 * @param s1 The first sprite (one with hitboxes)
	 * @param s2 The second sprite (one without hitboxes)
	 */
	protected abstract void spriteCollided(Sprite s1, Sprite s2);
	
	/**
	 * Defines behavior for a collision between the hitbox of one sprite and
	 * another sprite within the collision group.  This may be used when
	 * performing actions upon the members of the sprite group.  For actions
	 * to be taken by objects outside of the two associated sprite groups,
	 * the broadcast events should be used (or I guess you can use those for
	 * actions to be taken by sprites in the two sprite groups as well?)
	 * @param s1 Sprite from the first sprite group (one with hitboxes)
	 * @param h1 The hitbox in the collision (held by sprite s1)
	 * @param s2 Sprite from the second sprite group (one without hitboxes)
	 */
	protected abstract void hitboxSpriteCollided(Sprite s1, Hitbox h1, Sprite s2);

}

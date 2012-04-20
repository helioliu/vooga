package collisionType;

import sprites.Boxable;
import collisions.CollisionRect;
import collisions.CollisionShape;
import collisions.Hitbox;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.AdvanceCollisionGroup;

import core.EventManager;

/**
 * Defines a collision manager for a collision group that holds
 * two sprite groups that both contain sprites implements hitboxes
 * I suspect anything extending this in any meaningful way will be
 * inherently complicated due to the high number of combinations for
 * pairs of hitboxes
 */
public abstract class AbstractHitboxHitboxCollision extends ShapeCollision{
	
	
	
	/**
	 * Checks for hitbox-hitbox collisions, then hitbox-sprite collision,
	 * then sprite-hitbox collisions, then sprite-sprite collisions.
	 * Collision events are broadcast in their appropriate pairs for
	 * listening, and additional behavior may be defined internally.
	 */
	public void collided(Sprite s1, Sprite s2) {		
		//we're not using GTGE's collision shapes so we need to clone them, yo
		//this initializes cr1 and cr2, the bounds of sprites s1 and s2, respectively
		initCollisionShapes();
		
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
					hitboxCollided(s1, h1, s2, h2);
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
				spriteHitboxCollided(s1, s2, h2);
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
				hitboxSpriteCollided(s1, h1, s2);
			}
		}
		
		//sprite-sprite collisions
		EventManager.getEventManager().sendEvent("collision "+s1.getID()+" "+s2.getID());
		System.out.println("collision "+s1.getID()+" "+s2.getID());
		EventManager.getEventManager().sendEvent("collision "+s2.getID()+" "+s1.getID());
		System.out.println("collision "+s2.getID()+" "+s1.getID());
		spriteCollided(s1, s2);
	}
	
	/**
	 * Defines additional behavior for collisions between the two sprites
	 * not handled by the collided method.  Collisions specifically involving
	 * hitboxes should be handled by hitboxCollided
	 * @param s1 The sprite from the first sprite group
	 * @param s2 The sprite from the second sprite group
	 */
	protected abstract void spriteCollided(Sprite s1, Sprite s2);
	
	/**
	 * Defines additional behavior for collisions between a hitbox and a sprite
	 * @param s1 The sprite from the first sprite group
	 * @param h1 The hitbox colliding with s2 (held by s1)
	 * @param s2 The sprite from the second sprite group (being hit)
	 */
	protected abstract void hitboxSpriteCollided(Sprite s1, Hitbox h1, Sprite s2);
	
	/**
	 * Defines additional behavior for collisions between a sprite and a hitbox
	 * @param s1 The sprite from the first sprite group (being hit)
	 * @param s2 The sprite from the second sprite group
	 * @param h2 The hitbox colliding with s1 (held by s2)
	 */
	protected abstract void spriteHitboxCollided(Sprite s1, Sprite s2, Hitbox h2);

	/**
	 * Defines behavior for a collision between a hitbox of a sprite from
	 * the first collision group and another hitbox of a sprite from the
	 * second collision group.  This may be used when performing actions
	 * upon members of the collision group that collide.  For actions to
	 * be taken by objects outside of the two associated sprite groups,
	 * the broadcast events should be used (or I guess you can use those
	 * for actions to be taken by sprites in the two sprite groups as well?)
	 * @param s1 Sprite from the first sprite group
	 * @param h1 The hitbox in the collision (held by sprite s1)
	 * @param s2 Sprite from the second sprite group
	 * @param h2 The other hitbox in the collision (held by sprite s2)
	 */
	protected abstract void hitboxCollided(Sprite s1, Hitbox h1, Sprite s2, Hitbox h2);

}

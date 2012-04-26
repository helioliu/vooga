package collisionType;

import collisions.CollisionRect;

import com.golden.gamedev.object.collision.AdvanceCollisionGroup;

/**
 * All new collision types involving hitboxes utilize collisionshape classes
 * different from those provided by GTGE.  The purpose of this class is to
 * provide methods related to defining collisionshapes of the type provided
 * in this project.  This does not override methods related to collisionshapes
 * provided by classes higher in the hierarchy because they are used in
 * CollisionManagers etc (which we are using). CollisionManagers extending
 * this are supposed to receive sprite groups that have sprites with hitboxes.
 * These classes should check for normal sprite collisions, then check that
 * the sprites have valid hitbox lists (not null), and break right after if
 * they are not valid so we don't get cool exceptions.
 */
public abstract class ShapeCollision extends AdvanceCollisionGroup{
	
	/**
	 * The collisionshape of the sprite from the first group. This is already
	 * defined within AdvanceCollisionGroup, but since we're not using GTGE's
	 * native collisionshapes we have to recreate them in the form we're using.
	 * They are provided as protected so as not to interfere with the
	 * respective getters higher in the hierarchy
	 */
	protected CollisionRect cr1;
	
	/**
	 * The collisionshape of the sprite from the second group.
	 * Same reasoning as before (right up there).
	 * Don't really care if these get modified since they're just copies anyway?
	 */
	protected CollisionRect cr2;
	
	/**
	 * Convert the bounding rectangles of the two sprites into the project's
	 * collision shapes (instead of GTGE's native ones)
	 */
	protected void initCollisionShapes(){
		pixelPerfectCollision = true;
		cr1 = new CollisionRect(rect1.x, rect1.y, rect1.getHeight(), rect1.getWidth());
		cr2 = new CollisionRect(rect2.x, rect2.y, rect2.getHeight(), rect2.getWidth());
	}
	
	/**
	 * Switch the references for cr1 and cr2 in the case that we want them
	 * to correspond to sprites s2 and s1, respectively
	 */
	protected void switchShapes(){
		CollisionRect temp = cr1;
		cr1 = cr2;
		cr2 = temp;
	}
	
}

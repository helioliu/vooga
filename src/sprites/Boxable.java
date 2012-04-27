package sprites;

import java.util.List;

import collisions.Hitbox;

/**
 * Defines an interface for sprites that want to have hitbox
 * regions to define more specific collision behavior
 * Hitboxes should be defined at compile time
 */
public interface Boxable{
    
	/**
	 * Gets the internal list of hitboxes, preferrably
	 * in an unmodifiable way
	 */
    public List<Hitbox> getHitboxes();
}

package interactiveSprites;

import sprites.GeneralSprite;

import com.golden.gamedev.object.collision.CollisionGroup;

public interface InteractiveSprite {
	
	/**
	 * performs primary function of the interactive sprite type
	 */
	public void primaryAction(CollisionGroup c, GeneralSprite s);
	
	/**
	 * returns type of interactive sprite
	 */
	public String getType();
	

}

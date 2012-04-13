package interactiveSprites.interactiveExample;

import com.golden.gamedev.object.collision.CollisionGroup;

interface InteractiveSprite {
	
	/**
	 * performs primary function of the interactive sprite type
	 */
	public void primaryAction(CollisionGroup c);
	
	
	
	/**
	 * sets position of the interactive sprite to follow user sprite. i.e. lets user move sprite
	 */
	public void userMove();
	
	
	
	/**
	 * returns type of interactive sprite
	 */
	public String getType();
	
	

}
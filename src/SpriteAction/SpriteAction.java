package SpriteAction;

import com.golden.gamedev.object.Sprite;

public abstract class SpriteAction {
	protected Sprite mySprite;
	
	public SpriteAction(Sprite s)
	{
		mySprite = s;
	}
	
	public abstract void act();

}

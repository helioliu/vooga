package SpriteAction;

import com.golden.gamedev.object.Sprite;

import core.EventListener;

public abstract class SpriteAction implements EventListener{
	protected Sprite mySprite;
	
	public SpriteAction(Sprite s)
	{
		mySprite = s;
		
	}
	
	

}

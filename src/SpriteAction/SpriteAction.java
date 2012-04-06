package SpriteAction;

import com.golden.gamedev.object.Sprite;

import core.EventListener;
import core.EventManager;

public abstract class SpriteAction implements EventListener{
	protected Sprite mySprite;
	
	public SpriteAction(Sprite s)
	{
		mySprite = s;
		
	}
	
	public abstract void actionPerformed(String event);

}

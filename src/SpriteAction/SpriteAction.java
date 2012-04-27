package SpriteAction;

import sprites.GeneralSprite;

import com.golden.gamedev.object.Sprite;

import core.EventListener;

public abstract class SpriteAction implements EventListener{
	protected GeneralSprite mySprite;
	
	public SpriteAction(GeneralSprite s)
	{
		mySprite = s;
		
	}
	
	

}

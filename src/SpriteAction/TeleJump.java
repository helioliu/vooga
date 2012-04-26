package SpriteAction;

import core.EventManager;
import sprites.GeneralSprite;

public class TeleJump extends SpriteAction{

	public TeleJump(GeneralSprite s) {
		super(s);
		
	}
	
	public void actionPerformed(Object event)
	{
		mySprite.setLocation(mySprite.getX(), mySprite.getY()-100);
//		EventManager.getEventManager().sendEvent("jumped");
	}

}

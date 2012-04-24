package SpriteAction;

import sprites.GeneralSprite;

import com.golden.gamedev.object.Sprite;

import core.EventManager;




public class StandAction extends SpriteAction{
	
	public StandAction(GeneralSprite s) {
		super(s);
	}

	public void actionPerformed(Object event)
	{
		mySprite.setVerticalSpeed(0);
		//mySprite.setLocation(mySprite.getOldX(), mySprite.getOldY());
		//mySprite.setGravity(0);
		EventManager.getEventManager().sendEvent("landed");
	}

}

package SpriteAction;

import core.EventManager;
import sprites.GeneralSprite;

public class JumpAction extends SpriteAction{
	
	public JumpAction(GeneralSprite s) {
		super(s);
		
	}

	public void actionPerformed(Object event)
	{
		mySprite.setGravity(.002);
		mySprite.setVerticalSpeed(-0.75);
		EventManager.getEventManager().sendEvent("jumped");
	}

}

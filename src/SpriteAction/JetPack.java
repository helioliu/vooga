package SpriteAction;

import core.EventManager;
import sprites.GeneralSprite;

public class JetPack extends SpriteAction{

	public JetPack(GeneralSprite s) {
		super(s);
		
	}
	
	public void actionPerformed(String event)
	{
		mySprite.setVerticalSpeed(mySprite.getVerticalSpeed()-.05);

		EventManager.getEventManager().sendEvent("jumped");
	}

}

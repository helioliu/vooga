package SpriteAction;

import com.golden.gamedev.object.Sprite;

import core.EventManager;



public class StandAction extends SpriteAction{
	
	public StandAction(Sprite s) {
		super(s);
		EventManager em = EventManager.getEventManager();
		em.registerEventListener("floor collide", this);
	}

	public void actionPerformed(String event)
	{
		mySprite.setVerticalSpeed(0);
		mySprite.move(mySprite.getOldX(), mySprite.getOldY());	
	}

}

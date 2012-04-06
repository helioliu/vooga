package SpriteAction;

import com.golden.gamedev.object.Sprite;

import core.EventManager;

public class MoveLeft extends SpriteAction{
	
	public MoveLeft(Sprite s) {
		super(s);
		EventManager em = EventManager.getEventManager();
		em.registerEventListener("left-key", this);
	}

	public void actionPerformed(String event)
	{
		mySprite.move(mySprite.getOldX()-1, mySprite.getOldY());
	}

}

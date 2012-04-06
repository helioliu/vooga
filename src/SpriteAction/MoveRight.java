package SpriteAction;

import com.golden.gamedev.object.Sprite;

import core.EventManager;

public class MoveRight extends SpriteAction{
	
	public MoveRight(Sprite s) {
		super(s);
		EventManager em = EventManager.getEventManager();
		em.registerEventListener("right-key", this);
	}

	public void actionPerformed(String event)
	{
		mySprite.move(1,0);
	}

}

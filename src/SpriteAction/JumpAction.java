package SpriteAction;

import com.golden.gamedev.object.Sprite;

import core.EventManager;

public class JumpAction extends SpriteAction{
	
	public JumpAction(Sprite s) {
		super(s);
		EventManager em = EventManager.getEventManager();
		em.registerEventListener("up-key", this);
	}

	public void actionPerformed(String event)
	{
		mySprite.setVerticalSpeed(-0.75);
	}

}

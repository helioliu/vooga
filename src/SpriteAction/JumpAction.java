package SpriteAction;

import sprites.GeneralSprite;

public class JumpAction extends SpriteAction{
	
	public JumpAction(GeneralSprite s) {
		super(s);
		
	}

	public void actionPerformed(Object event)
	{
		mySprite.setVerticalSpeed(-0.75);
	}

}

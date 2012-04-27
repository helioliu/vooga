package SpriteAction;

import sprites.GeneralSprite;

public class MoveLeft extends SpriteAction{
	
	public MoveLeft(GeneralSprite s) {
		super(s);
	}

	public void actionPerformed(String event)
	{
		mySprite.move(-1.7,0);
		mySprite.setFrame(3);
	}

}

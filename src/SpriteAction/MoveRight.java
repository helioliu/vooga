package SpriteAction;

import sprites.GeneralSprite;

public class MoveRight extends SpriteAction{
	
	public MoveRight(GeneralSprite s) {
		super(s);
	}

	public void actionPerformed(String event)
	{
		mySprite.move(1.7,0);
		mySprite.setFrame(0);
	}



}

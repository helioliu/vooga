package SpriteAction;

import sprites.GeneralSprite;

public class MoveRight extends SpriteAction{
	
	public MoveRight(GeneralSprite s) {
		super(s);
	}

	public void actionPerformed(Object event)
	{
		mySprite.move(1,0);
	}



}

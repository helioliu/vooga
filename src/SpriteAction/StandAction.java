package SpriteAction;

import sprites.GeneralSprite;

import com.golden.gamedev.object.Sprite;




public class StandAction extends SpriteAction{
	
	public StandAction(GeneralSprite s) {
		super(s);
	}

	public void actionPerformed(Object event)
	{
		mySprite.setVerticalSpeed(0);
		mySprite.setLocation(mySprite.getOldX(), mySprite.getOldY());	
	}

}

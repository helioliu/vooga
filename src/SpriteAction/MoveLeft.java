package SpriteAction;

import com.golden.gamedev.object.Sprite;

public class MoveLeft extends SpriteAction{
	
	public MoveLeft(Sprite s) {
		super(s);
	}

	public void act()
	{
		mySprite.move(mySprite.getOldX()-1, mySprite.getOldY());
	}

}

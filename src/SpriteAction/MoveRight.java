package SpriteAction;

import com.golden.gamedev.object.Sprite;

public class MoveRight extends SpriteAction{
	
	public MoveRight(Sprite s) {
		super(s);
	}

	public void act()
	{
		mySprite.move(mySprite.getOldX()+1, mySprite.getOldY());
	}

}

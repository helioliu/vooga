package SpriteAction;

import com.golden.gamedev.object.Sprite;


public class MoveLeft extends SpriteAction{
	
	public MoveLeft(Sprite s) {
		super(s);
	}

	public void actionPerformed(Object event)
	{
		mySprite.move(-1,0);
	}

}

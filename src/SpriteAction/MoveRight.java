package SpriteAction;

import com.golden.gamedev.object.Sprite;


public class MoveRight extends SpriteAction{
	
	public MoveRight(Sprite s) {
		super(s);
	}

	public void actionPerformed(Object event)
	{
		mySprite.move(1,0);
	}



}

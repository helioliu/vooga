package SpriteAction;

import com.golden.gamedev.object.Sprite;

public class JumpAction extends SpriteAction{
	
	public JumpAction(Sprite s) {
		super(s);
	}

	public void act()
	{
		mySprite.setVerticalSpeed(-0.75);
	}

}

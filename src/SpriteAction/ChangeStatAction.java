package SpriteAction;

import sprites.GeneralSprite;

import com.golden.gamedev.object.Sprite;

public class ChangeStatAction extends SpriteAction{
	
	public ChangeStatAction(GeneralSprite s) {
		super(s);
	}

	@Override
	public void actionPerformed(Object event) {
		mySprite.changeStat("health", 20);
	}

}

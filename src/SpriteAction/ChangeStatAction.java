package SpriteAction;

import sprites.GeneralSprite;

import com.golden.gamedev.object.Sprite;

public class ChangeStatAction extends SpriteAction{
	
	public ChangeStatAction(GeneralSprite s) {
		super(s);
	}

	@Override
	public void actionPerformed(Object event) {
		mySprite.changeStat("health", -.2);
		mySprite.changeStat("mana", -.7);
		mySprite.changeStat("score", mySprite.getStat("score").getMyValue() + 5);
	}

}

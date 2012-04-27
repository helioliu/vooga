package SpriteAction;

import sprites.GeneralSprite;

public class AddLife extends SpriteAction{

	public AddLife(GeneralSprite s) {
		super(s);
	}

	@Override
	public void actionPerformed(Object object) {
		mySprite.changeStat("lives",1);
		
	}

}

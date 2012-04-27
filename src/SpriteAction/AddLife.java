package SpriteAction;

import sprites.GeneralSprite;

public class AddLife extends SpriteAction{

	public AddLife(GeneralSprite s) {
		super(s);
	}


	public void actionPerformed(String object) {
		mySprite.getStat("lives").adjust(1);

	}

}

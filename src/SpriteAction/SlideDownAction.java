package SpriteAction;

import sprites.GeneralSprite;

public class SlideDownAction extends SpriteAction {

	public SlideDownAction(GeneralSprite s) {
		super(s);
	}

	public void actionPerformed(Object object) {
		mySprite.setGravity(0);
		mySprite.setVerticalSpeed(.055);
	}

}

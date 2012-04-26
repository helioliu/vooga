package States;

import SpriteAction.TeleJump;
import sprites.GeneralSprite;

public class TeleJumpPowerup extends State{

	public TeleJumpPowerup(GeneralSprite s) {
		super(s);
		addAction("Space", new TeleJump(s));
	}

}

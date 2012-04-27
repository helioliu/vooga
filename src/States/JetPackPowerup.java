package States;

import SpriteAction.JetPack;
import sprites.GeneralSprite;

public class JetPackPowerup extends State{

	public JetPackPowerup(GeneralSprite s) {
		super(s);
		addAction("Space", new JetPack(s));
	}

}

package States;

import sprites.GeneralSprite;
import SpriteAction.JumpAction;

public class DeadState extends State {

	public DeadState(GeneralSprite s) {
		super(s);
		s.setFrame(2);
		addAction("Up", new JumpAction(s));
		
		
	}
	
	

}

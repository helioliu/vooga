package States;

import core.EventManager;
import cutscenes.Cutscene;
import cutscenes.CutsceneAutomation;
import cutscenes.EventAutomation;
import SpriteAction.JumpAction;
import sprites.GeneralSprite;

public class DeadState extends State {

	public DeadState(GeneralSprite s) {
		super(s);
		s.setFrame(2);
		addAction("Up", new JumpAction(s));
		
		
	}
	
	

}

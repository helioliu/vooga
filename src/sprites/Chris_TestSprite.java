package sprites;

import StateMachines.CharacterStateMachine;
import StateMachines.StateMachine;

import com.golden.gamedev.object.Sprite;

public class Chris_TestSprite extends Sprite{
	private StateMachine stateManager;
	
	public Chris_TestSprite()
	{
		super();
		stateManager = new CharacterStateMachine(((Sprite) this));
		
	}

}

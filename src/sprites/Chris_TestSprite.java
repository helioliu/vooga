package sprites;

import StateMachines.CharacterStateMachine;
import StateMachines.StateMachine;

import com.golden.gamedev.object.Sprite;

public class Chris_TestSprite extends GeneralSprite{
	
	
	public Chris_TestSprite()
	{
		super();
		myStateMachine = new CharacterStateMachine(((Sprite) this));
		myGravityValue = 0.002;
	}
	
	public void update(long elapsedTime)
	{
		this.addVerticalSpeed(elapsedTime, .002, 0.5);
	}

}

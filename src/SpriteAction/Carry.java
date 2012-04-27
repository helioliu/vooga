package SpriteAction;

import sprites.GeneralSprite;
import stateTransitions.ChangeStateTransition;
import stateTransitions.StateTransition;
import States.InteractiveSpriteStates.CarryingState;

import com.golden.gamedev.object.Sprite;

import core.EventManager;


public class Carry extends SpriteAction{
	
	public Carry(GeneralSprite s) {
		super(s);
		
		
	}

	public void actionPerformed(Object event)
	{
		EventManager.getEventManager().sendEvent("toCarrying");
		
	}

}
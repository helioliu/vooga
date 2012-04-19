package stateManagers;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import stateTransitions.ChangeStateTransition;
import stateTransitions.StateTransition;
import stateTransitions.SwitchStatesTransition;

import com.golden.gamedev.object.Sprite;

import States.*;

public class CharacterStateManager extends StateManager{
	private Sprite mySprite;

	private State currentState;

	public CharacterStateManager(Sprite s, State startingState)
	{
		super(s, startingState);
		getTransitions().add(new ChangeStateTransition(this, "switchstates", new RegularMotionState(s)));
	}
	
	




}

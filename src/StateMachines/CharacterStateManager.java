package StateMachines;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import stateTransitions.ChangeStateTransition;
import stateTransitions.SwitchStatesTransition;

import com.golden.gamedev.object.Sprite;

import States.*;

public class CharacterStateManager extends StateManager{

	public CharacterStateManager(Sprite s)
	{
		super(s);
		currentState = new OnLandState(s);
		currentState.activateAllListeners();
		//possibleStateTransitions.add(new ChangeStateTransition(this, new RegularMotionState(s)));
	}
	
	




}

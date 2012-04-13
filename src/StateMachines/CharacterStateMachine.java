package StateMachines;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import stateTransitions.SwitchStatesTransition;

import com.golden.gamedev.object.Sprite;

import States.*;

public class CharacterStateMachine extends StateMachine{

	public CharacterStateMachine(Sprite s)
	{
		super(s);
		currentState = new RegularMotionState(s);
		currentState.activateAllListeners();
		possibleStateTransitions.add(new SwitchStatesTransition(this, currentState, new ReverseMotionState(s)));
	}
	
	




}

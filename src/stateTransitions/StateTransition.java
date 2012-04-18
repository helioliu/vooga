package stateTransitions;

import core.EventListener;
import StateMachines.StateManager;
import States.State;

public abstract class StateTransition implements EventListener{
	protected StateManager myStateMachine;
	
	
	public StateTransition(StateManager sm)
	{
		myStateMachine = sm;
	}

}

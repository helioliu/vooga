package stateTransitions;

import core.EventListener;
import StateMachines.StateMachine;
import States.State;

public abstract class StateTransition implements EventListener{
	protected StateMachine myStateMachine;
	
	
	public StateTransition(StateMachine sm)
	{
		myStateMachine = sm;
	}

}

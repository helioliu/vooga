package stateTransitions;

import core.EventListener;
import core.EventManager;
import stateManagers.StateManager;
import States.State;

public abstract class StateTransition implements EventListener{
	private StateManager myStateMachine;
	private State myState;
	
	
	public StateTransition(StateManager sm, String event, State s)
	{
		myStateMachine = sm;
		EventManager.getEventManager().registerEventListener(event, this);
	}
	
	protected StateManager getMyStateMachine() {
        return myStateMachine;
    }
	
	protected State getMyState(){
	    return myState;
	}
	
	

}

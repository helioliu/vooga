package stateTransitions;

import core.EventListener;
import core.EventManager;
import stateManagers.StateManager;
import States.State;

public abstract class StateTransition implements EventListener{
	private StateManager myStateManager;
	private State myState;
	private String myEvent;
	
	public StateTransition(StateManager sm, String event, State s)
	{
		myStateManager = sm;
		myState = s;
		myEvent = event;
	}
	
	protected StateManager getMyStateManager() {
        return myStateManager;
    }
	
	protected State getMyState(){
	    return myState;
	}
	
	public void activate(){
	    EventManager.getEventManager().registerEventListener(myEvent, this);
	}
	
	public void deactivate(){
	    EventManager.getEventManager().unregisterEventListener(myEvent, this);
	}
	
	

}

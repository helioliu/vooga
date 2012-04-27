package stateTransitions;

import core.EventManager;
import stateManagers.StateManager;
import States.State;
//in progress do not use yet
public class AlternateStatesTransition extends StateTransition{
	private State state2;

	public AlternateStatesTransition(StateManager sm, String event, State s1, State s2) {
		super(sm, event, s1 );
		state2 = s2;
	}

	public void actionPerformed(String eventName) {
		if(getMyStateManager().isCurrentlyActive(getMyState()))
		{
			getMyStateManager().replaceState(state2, getMyState());
		}
		else{
			getMyStateManager().replaceState(getMyState(),state2);
		}
		
	}

}


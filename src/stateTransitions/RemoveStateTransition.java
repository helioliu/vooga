package stateTransitions;

import stateManagers.StateManager;
import States.State;

public class RemoveStateTransition extends StateTransition{

	public RemoveStateTransition(StateManager sm, String event, State s) {
		super(sm, event, s);
	}


	public void actionPerformed(String eventName) {
		this.getMyStateManager().addState(getMyState());
		
	}
}

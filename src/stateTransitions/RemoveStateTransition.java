package stateTransitions;

import stateManagers.StateManager;
import States.State;

public class RemoveStateTransition extends StateTransition{

	public RemoveStateTransition(StateManager sm, String event, State s) {
		super(sm, event, s);
	}

	@Override
	public void actionPerformed(Object eventName) {
		this.getMyStateManager().addState(getMyState());
		
	}
}

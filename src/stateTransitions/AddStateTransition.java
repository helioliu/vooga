package stateTransitions;

import stateManagers.StateManager;
import States.State;

public class AddStateTransition extends StateTransition{
	

	public AddStateTransition(StateManager sm, String event, State s) {
		super(sm, event, s);

		
	}

	@Override
	public void actionPerformed(Object eventName) {
		this.getMyStateManager().addState(getMyState());
		
	}

}
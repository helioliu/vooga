package stateTransitions;

import core.EventManager;
import stateManagers.StateManager;
import States.State;

public class ChangeStateTransition extends StateTransition{
	

	public ChangeStateTransition(StateManager sm, String event, State s) {
		super(sm, event, s);

		
	}

	@Override
	public void actionPerformed(Object eventName) {
		this.getMyStateManager().changeState(getMyState());
		
	}

}

package stateTransitions;

import stateManagers.StateManager;
import States.State;

public class ReplaceStateTransition extends StateTransition{
	private State toRemove;
	public ReplaceStateTransition(StateManager sm, String event, State stateToAdd, State stateToRemove ) {
		super(sm, event, stateToAdd);
		toRemove = stateToRemove;

	}

	@Override
	public void actionPerformed(String eventName) {
		if(getMyStateManager().isCurrentlyActive(toRemove))
		{
			this.getMyStateManager().replaceState(getMyState(), toRemove);
		}
		
		
	}

}

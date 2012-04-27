
package stateTransitions;

import core.EventManager;
import stateManagers.StateManager;
import States.State;

public class ChangeStateTransition extends StateTransition{
	

	public ChangeStateTransition(StateManager sm, String event, State s) {
		super(sm, event, s);

		
	}


	public void actionPerformed(String eventName) {
		this.getMyStateManager().changeState(getMyState());
		
	}

}


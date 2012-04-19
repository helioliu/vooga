package stateTransitions;

import core.EventManager;
import stateManagers.StateManager;
import States.State;

public class ChangeStateTransition extends StateTransition{
	protected State stateToChangeTo;

	public ChangeStateTransition(StateManager sm, String event, State s) {
		super(sm, event, s);
		//stateToChangeTo = s;
		//EventManager.getEventManager().registerEventListener("switchstates", this);
		
	}

	@Override
	public void actionPerformed(Object eventName) {
		this.getMyStateMachine().changeState(stateToChangeTo);
		
	}

}

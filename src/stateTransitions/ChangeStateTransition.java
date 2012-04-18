package stateTransitions;

import core.EventManager;
import StateMachines.StateManager;
import States.State;

public class ChangeStateTransition extends StateTransition{
	protected State stateToChangeTo;

	public ChangeStateTransition(StateManager sm, State s) {
		super(sm);
		stateToChangeTo = s;
		EventManager.getEventManager().registerEventListener("switchstates", this);
		
	}

	@Override
	public void actionPerformed(Object eventName) {
		myStateMachine.changeState(stateToChangeTo);
		
	}

}

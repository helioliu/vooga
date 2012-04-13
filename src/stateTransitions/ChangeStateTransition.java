package stateTransitions;

import core.EventManager;
import StateMachines.StateMachine;
import States.State;

public class ChangeStateTransition extends StateTransition{
	protected State stateToChangeTo;

	public ChangeStateTransition(StateMachine sm, State s) {
		super(sm);
		stateToChangeTo = s;
		EventManager.getEventManager().registerEventListener("switchstates", this);
		
	}

	@Override
	public void actionPerformed(String eventName) {
		myStateMachine.changeState(stateToChangeTo);
		
	}

}

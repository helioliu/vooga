package stateTransitions;

import StateMachines.StateMachine;
import States.State;

public class ChangeStateTransition extends StateTransition{
	protected State stateToChangeTo;

	public ChangeStateTransition(StateMachine sm) {
		super(sm);
		
	}

	@Override
	public void actionPerformed(String eventName) {
		myStateMachine.changeState(stateToChangeTo);
		
	}

}

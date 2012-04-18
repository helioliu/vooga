package stateTransitions;

import core.EventManager;
import StateMachines.StateManager;
import States.State;

public class SwitchStatesTransition extends StateTransition{
	protected State state1;
	protected State state2;

	public SwitchStatesTransition(StateManager sm, State s1, State s2) {
		super(sm);
		state1 = s1;
		state2 = s2;
		EventManager.getEventManager().registerEventListener("switchstates", this);
	}

	@Override
	public void actionPerformed(Object eventName) {
		System.out.println("should switch");
		if(myStateMachine.compareToCurrent(state1))
		{
			myStateMachine.changeState(state2);
		}
		if(myStateMachine.compareToCurrent(state2))
		{
			myStateMachine.changeState(state1);
		}
		
	}

}


package stateTransitions;

import core.EventManager;
import stateManagers.StateManager;
import States.State;
//in progress do not use yet
public class SwitchStatesTransition extends StateTransition{
	protected State state1;
	protected State state2;

	public SwitchStatesTransition(StateManager sm, String event, State s1, State s2) {
		super(sm, event, s1 );
		state2 = s2;
	}

	@Override
	public void actionPerformed(Object eventName) {
		System.out.println("should switch");
		if(getMyStateMachine().isCurrentlyActive(getMyState()))
		{
			getMyStateMachine().changeState(state2);
		}
		else{
			getMyStateMachine().changeState(getMyState());
		}
		
	}

}


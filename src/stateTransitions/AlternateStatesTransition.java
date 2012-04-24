package stateTransitions;

import core.EventManager;
import stateManagers.StateManager;
import States.State;
//in progress do not use yet
public class AlternateStatesTransition extends StateTransition{
	protected State state1;
	protected State state2;

	public AlternateStatesTransition(StateManager sm, String event, State s1, State s2) {
		super(sm, event, s1 );
		state2 = s2;
	}

	@Override
	public void actionPerformed(Object eventName) {
		if(getMyStateManager().isCurrentlyActive(getMyState()))
		{
			System.out.println("Onland is active");
			getMyStateManager().changeState(state2);
		}
		else{
			getMyStateManager().changeState(getMyState());
		}
		
	}

}


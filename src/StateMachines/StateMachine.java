package StateMachines;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import States.State;


public abstract class StateMachine implements EventListener{
	
	protected ArrayList<State> possibleStates;
	protected State currentState;
	
	
	public abstract void perform(ActionEvent ae);
	

}

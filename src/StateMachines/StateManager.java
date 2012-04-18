package StateMachines;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import stateTransitions.StateTransition;

import States.State;

import com.golden.gamedev.object.Sprite;

import core.EventListener;




public abstract class StateManager{
	protected Sprite mySprite;
	protected ArrayList<StateTransition> possibleStateTransitions;
	protected State currentState;
	
	public StateManager(Sprite s)
	{
		mySprite = s;
		possibleStateTransitions = new ArrayList<StateTransition>();
	}
	
	public void changeState(State s)
	{
		currentState.deactivateAllListeners();
		currentState = s;
		currentState.activateAllListeners();
	}
	
	public boolean compareToCurrent(State s)
	{
		return currentState.hashCode()==s.hashCode();
	}
	
	

}

package stateManagers;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import stateTransitions.StateTransition;

import States.State;

import com.golden.gamedev.object.Sprite;

import core.EventListener;




public abstract class StateManager{
	private Sprite mySprite;
	private ArrayList<StateTransition> myStateTransitions;
	private State currentState;
	
	public StateManager(Sprite s)
	{
		mySprite = s;
		myStateTransitions = new ArrayList<StateTransition>();
	}
	
	public void addTransition(StateTransition toAdd)
	{
		myStateTransitions.add(toAdd);
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

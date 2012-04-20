package stateManagers;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import stateTransitions.StateTransition;

import States.State;

import com.golden.gamedev.object.Sprite;

import core.EventListener;




public class StateManager{
	private Sprite mySprite;

	private State currentState;
	
	public StateManager(Sprite s, State startingState)
	{
		mySprite = s;
		currentState = startingState;
		currentState.activateAllListeners();
	}
	

	
	public void changeState(State s)
	{
		if(currentState!=s)
		{
			currentState.deactivateAllListeners();
			currentState = s;
			currentState.activateAllListeners();
		}
		
	}
	
	public boolean compareToCurrent(State s)
	{
		return currentState.equals(s);
	}

	
	

}

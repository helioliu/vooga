package stateManagers;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import stateTransitions.StateTransition;

import States.State;

import com.golden.gamedev.object.Sprite;

import core.EventListener;




public class StateManager{
	private Sprite mySprite;

	private List<State> currentStates;
	
	public StateManager(Sprite s, State startingState)
	{
		mySprite = s;
		currentStates.add(startingState);
		currentStates.get(0).activateAllListeners();
	}
	

	public void addState(State s)
	{
		currentStates.add(s);
		s.activateAllListeners();
	}
	public void removeState(State s)
	{
		currentStates.remove(s);
		s.deactivateAllListeners();
	}
	public void changeState(State s)
	{
		for(State cur : currentStates)
		{
			cur.deactivateAllListeners();
		}
		currentStates.clear();
		s.activateAllListeners();
		currentStates.add(s);
		
		
	}
	
	public boolean isCurrentlyActive(State s)
	{
		for(State cur : currentStates)
		{
			if(cur.equals(s))
			{
				return true;
			}
		}
		return false;
	}

	
	

}

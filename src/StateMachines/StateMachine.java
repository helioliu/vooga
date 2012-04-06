package StateMachines;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import States.State;

import com.golden.gamedev.object.Sprite;

import core.EventListener;




public abstract class StateMachine implements EventListener{
	protected Sprite mySprite;
	protected ArrayList<State> possibleStates;
	protected State currentState;
	
	
	

}

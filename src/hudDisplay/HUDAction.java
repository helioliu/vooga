package hudDisplay;
import sprites.TestCharacterWithStates;

import core.EventListener;



public abstract class HUDAction implements EventListener{
	protected TestCharacterWithStates mySprite;
	
	public HUDAction( TestCharacterWithStates s)
	{
		mySprite = s;
		
	}
	
	public abstract void actionPerformed(String event);

}
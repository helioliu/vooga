package States.InteractiveSpriteStates;

import sprites.GeneralSprite;
import SpriteAction.Carry;
import States.State;


public class TouchingState extends State{
	
	public TouchingState(GeneralSprite s)
	{
		super(s);
		addAction("Z", new Carry(s));
		//setMyGravityValue(.05);
	}
	public void setGravity()
	{
		(getSprite()).setGravity(getMyGravityValue());
	}

} 



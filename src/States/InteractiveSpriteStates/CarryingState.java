package States.InteractiveSpriteStates;

import javax.swing.JComponent;
import sprites.GeneralSprite;
import SpriteAction.JumpAction;
import SpriteAction.MoveDown;
import SpriteAction.MoveLeft;
import SpriteAction.MoveRight;
import SpriteAction.MoveUp;
import SpriteAction.StandAction;
import SpriteAction.Stationary;
import SpriteAction.ThrowAction;
import States.State;


public class CarryingState extends State{
	
	public CarryingState(GeneralSprite s)
	{
		super(s);
		addAction("Up", new MoveUp(s));
		addAction("Right", new MoveRight(s));
		addAction("Left", new MoveLeft(s));
		addAction("Down", new MoveDown(s));
		addAction("C", new ThrowAction(s));
		//setMyGravityValue(.05);
	}
	public void setGravity()
	{
		((GeneralSprite) getSprite()).setGravity(getMyGravityValue());
	}

} 



package States.InteractiveSpriteStates;

import javax.swing.JComponent;

import sprites.GeneralSprite;
import SpriteAction.JumpAction;
import SpriteAction.MoveLeft;
import SpriteAction.MoveRight;
import SpriteAction.StandAction;
import States.State;

import com.golden.gamedev.object.Sprite;

public class CarryingState extends State{
	
	public CarryingState(GeneralSprite s)
	{
		super(s);
		addAction("Up", new JumpAction(s));
		addAction("Right", new MoveRight(s));
		addAction("Left", new MoveLeft(s));
		addAction("floor collide", new StandAction(s));
		setMyGravityValue(.05);
	}
	public void setGravity()
	{
		((GeneralSprite) mySprite).setGravity(myGravityValue);
	}
	@Override
	protected boolean isInState(JComponent arg0) {
		// TODO Auto-generated method stub
		return false;
	}
} 



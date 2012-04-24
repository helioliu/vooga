package States;

import sprites.GeneralSprite;
import SpriteAction.JumpAction;
import SpriteAction.MoveLeft;
import SpriteAction.MoveRight;
import SpriteAction.StandAction;

public class InAirState extends State {
	public InAirState(GeneralSprite s)
	{
		super(s);
		addAction("Right", new MoveRight(s));
		addAction("Left", new MoveLeft(s));
		addAction("floor collide", new StandAction(s));
		//addAction("got hit", new DecrementBarAction((TestCharacterWithStates) s));
		setMyGravityValue(0.002);
	}

}

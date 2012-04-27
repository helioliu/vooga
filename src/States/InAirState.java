package States;

import sprites.GeneralSprite;
import SpriteAction.ChangeImage;
import SpriteAction.JumpAction;
import SpriteAction.MoveLeft;
import SpriteAction.MoveRight;
import SpriteAction.SlideDownAction;
import SpriteAction.StandAction;

public class InAirState extends State {
	public InAirState(GeneralSprite s)
	{
		super(s);
		addAction("Right", new MoveRight(s));
		addAction("Left", new MoveLeft(s));
		addAction("floor collide", new StandAction(s));
		addAction("slide-down-pole", new SlideDownAction(s));
		addAction("mushroom", new ChangeImage(s));
		//addAction("got hit", new DecrementBarAction((TestCharacterWithStates) s));
		s.setGravity(0.002);
	}

}

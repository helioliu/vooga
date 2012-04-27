package States;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import sprites.GeneralSprite;
import sprites.StateSprite;
import stateTransitions.ChangeStateTransition;
import SpriteAction.AddLife;
import SpriteAction.ChangeImage;
import SpriteAction.JumpAction;
import SpriteAction.MoveLeft;
import SpriteAction.MoveRight;
import SpriteAction.SlideDownAction;
import SpriteAction.StandAction;

public class InAirState extends State {
	public InAirState(StateSprite s)
	{
		super(s);
		addAction("Right", new MoveRight(s));
		addAction("Left", new MoveLeft(s));
		addAction("floor collide", new StandAction(s));
		addAction("slide-down-pole", new SlideDownAction(s));
		addAction("mushroom", new AddLife(s));

		//addAction("got hit", new DecrementBarAction((TestCharacterWithStates) s));
		s.setGravity(0.002);
	}

}

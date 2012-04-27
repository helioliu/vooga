package States;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import sprites.GeneralSprite;
import sprites.StateSprite;
import stateTransitions.ChangeStateTransition;

import com.golden.gamedev.object.Sprite;

import SpriteAction.AddLife;
import SpriteAction.ChangeImage;
import SpriteAction.JumpAction;
import SpriteAction.MoveLeft;
import SpriteAction.MoveRight;
import SpriteAction.StandAction;

public class OnLandState extends State{
	
	public OnLandState(StateSprite s)
	{
		super(s);
		addAction("Up", new JumpAction(s));
		addAction("Right", new MoveRight(s));
		addAction("Left", new MoveLeft(s));
		addAction("floor collide", new StandAction(s));
		addAction("mushroom", new AddLife(s));

		//addAction("got hit", new DecrementBarAction((TestCharacterWithStates) s));
		s.setGravity(0.002);
	}
	
}

package States;

import sprites.GeneralSprite;

import com.golden.gamedev.object.Sprite;

import SpriteAction.ChangeImage;
import SpriteAction.JumpAction;
import SpriteAction.MoveLeft;
import SpriteAction.MoveRight;
import SpriteAction.StandAction;

public class OnLandState extends State{
	
	public OnLandState(GeneralSprite s)
	{
		super(s);
		addAction("Up", new JumpAction(s));
		addAction("Right", new MoveRight(s));
		addAction("Left", new MoveLeft(s));
		addAction("floor collide", new StandAction(s));
		addAction("mushroom", new ChangeImage(s));
		//addAction("got hit", new DecrementBarAction((TestCharacterWithStates) s));
		s.setGravity(0.002);
	}
	
}

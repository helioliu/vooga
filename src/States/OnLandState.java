package States;

import sprites.TestCharacterWithStates;
import hudDisplay.DecrementBarAction;

import com.golden.gamedev.object.Sprite;

import SpriteAction.JumpAction;
import SpriteAction.MoveLeft;
import SpriteAction.MoveRight;
import SpriteAction.StandAction;
import States.CharacterState;

public class OnLandState extends CharacterState{
	
	public OnLandState(Sprite s)
	{
		super(s);
		System.out.println(myMap);
		myMap.put("up", new JumpAction(s));
		myMap.put("right", new MoveRight(s));
		myMap.put("left", new MoveLeft(s));
		myMap.put("floor collide", new StandAction(s));
		myMap.put("got hit", new DecrementBarAction((TestCharacterWithStates) s));
	}
}

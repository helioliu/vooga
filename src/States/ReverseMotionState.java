package States;

import sprites.GeneralSprite;
import SpriteAction.ChangeStatAction;
import SpriteAction.MoveDown;
import SpriteAction.MoveLeft;
import SpriteAction.MoveRight;
import SpriteAction.MoveUp;
import SpriteAction.StandAction;

import com.golden.gamedev.object.Sprite;

public class ReverseMotionState extends State{
	
	public ReverseMotionState(GeneralSprite s) {
        super(s);
        addAction("Up", new MoveDown(s));
        addAction("Down", new MoveUp(s));
        addAction("Left", new MoveRight(s));
        addAction("Right", new MoveLeft(s));
        addAction("floor collide", new StandAction(s));
        addAction("got hit", new ChangeStatAction(s));
        
        
    }

}

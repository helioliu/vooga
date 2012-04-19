package States;

import SpriteAction.MoveDown;
import SpriteAction.MoveLeft;
import SpriteAction.MoveRight;
import SpriteAction.MoveUp;
import SpriteAction.StandAction;

import com.golden.gamedev.object.Sprite;

public class ReverseMotionState extends State{
	
	public ReverseMotionState(Sprite s) {
        super(s);
        myMap.put("Up", new MoveDown(s));
        myMap.put("Down", new MoveUp(s));
        myMap.put("Left", new MoveRight(s));
        myMap.put("Right", new MoveLeft(s));
        myMap.put("floor collide", new StandAction(s));
        
        
    }

}

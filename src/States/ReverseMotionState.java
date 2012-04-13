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
        myMap.put("up", new MoveDown(s));
        myMap.put("down", new MoveUp(s));
        myMap.put("left", new MoveRight(s));
        myMap.put("right", new MoveLeft(s));
        myMap.put("floor collide", new StandAction(s));
        
        
    }

}

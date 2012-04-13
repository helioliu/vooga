package States;

import SpriteAction.*;


import com.golden.gamedev.object.Sprite;

public class RegularMotionState extends EnemyState {
    
    public RegularMotionState(Sprite s) {
        super(s);
        myMap.put("up", new MoveUp(s));
        myMap.put("down", new MoveDown(s));
        myMap.put("left", new MoveLeft(s));
        myMap.put("right", new MoveRight(s));
        myMap.put("floor collide", new StandAction(s));
        myGravityValue = 0;
        
    }
   

}

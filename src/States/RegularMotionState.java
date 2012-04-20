package States;

import SpriteAction.*;


import com.golden.gamedev.object.Sprite;

public class RegularMotionState extends EnemyState {
    
    public RegularMotionState(Sprite s) {
        super(s);
        myMap.put("Up", new MoveUp(s));
        myMap.put("Down", new MoveDown(s));
        myMap.put("Left", new MoveLeft(s));
        myMap.put("Right", new MoveRight(s));
        myMap.put("floor collide", new StandAction(s));
        myGravityValue = 0;
        
    }
   

}

package States;

import SpriteAction.WalkDown;


import com.golden.gamedev.object.Sprite;

public class WalkingDownState extends EnemyState {
    
    public WalkingDownState(Sprite s) {
        super(s);
        myMap.put("move down", new WalkDown(s));
        
    }
   

}

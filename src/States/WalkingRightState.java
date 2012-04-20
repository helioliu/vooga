package States;

import SpriteAction.WalkRight;

import com.golden.gamedev.object.Sprite;

public class WalkingRightState extends EnemyState {
      
    public WalkingRightState(Sprite s){
        super(s);
        myMap.put("walk right", new WalkRight(s));
    }
}

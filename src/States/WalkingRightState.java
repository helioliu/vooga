package States;

import SpriteAction.WalkLeft;

import com.golden.gamedev.object.Sprite;

public class WalkingRightState extends EnemyState {
    
    
    public WalkingRightState(Sprite s){
        super(s);
        myMap.put("walk right", new WalkLeft(s));
    }
   
    
   

}

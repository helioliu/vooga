package States;

import SpriteAction.WalkUp;

import com.golden.gamedev.object.Sprite;

public class WalkingUpState extends EnemyState {
    
    public WalkingUpState(Sprite s){
        super(s);
        myMap.put("walk up", new WalkUp(s));
    }
}
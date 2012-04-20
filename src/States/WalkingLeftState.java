package States;

import SpriteAction.WalkLeft;

import com.golden.gamedev.object.Sprite;

public class WalkingLeftState extends EnemyState {
    
    public WalkingLeftState(Sprite s){
        super(s);
        myMap.put("walk left", new WalkLeft(s));
    }
}

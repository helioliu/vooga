package States;

import sprites.GeneralSprite;
import SpriteAction.WalkRight;

import com.golden.gamedev.object.Sprite;

public class WalkingRightState extends EnemyState {
      
    public WalkingRightState(GeneralSprite s){
        super(s);
        addAction("walk right", new WalkRight(s));
    }
}

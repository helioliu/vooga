package States;

import sprites.GeneralSprite;
import SpriteAction.WalkRight;

import com.golden.gamedev.object.Sprite;

public class WalkingRightState extends State {
      
    public WalkingRightState(GeneralSprite s){
        super(s);
        addAction("walk right" + s.hashCode(), new WalkRight(s));
    }
}

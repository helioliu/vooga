package States;

import sprites.GeneralSprite;
import SpriteAction.WalkLeft;

import com.golden.gamedev.object.Sprite;

public class WalkingLeftState extends EnemyState {
    
    public WalkingLeftState(GeneralSprite s){
        super(s);
        addAction("walk left", new WalkLeft(s));
    }
}

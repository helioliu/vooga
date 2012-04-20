package States;

import sprites.GeneralSprite;
import SpriteAction.WalkLeft;



public class WalkingLeftState extends EnemyState {
    
    public WalkingLeftState(GeneralSprite s){
        super(s);
        addAction("walk left", new WalkLeft(s));
    }
}

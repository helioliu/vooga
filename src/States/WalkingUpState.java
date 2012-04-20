package States;

import sprites.GeneralSprite;
import SpriteAction.WalkUp;


public class WalkingUpState extends EnemyState {
    
    public WalkingUpState(GeneralSprite s){
        super(s);
        addAction("walk up", new WalkUp(s));
    }
}
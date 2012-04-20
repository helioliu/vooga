package States;

import sprites.GeneralSprite;
import SpriteAction.WalkUp;



public class GoUpState extends EnemyState {
    
    public GoUpState(GeneralSprite s) {
        super(s);
        addAction("move up", new WalkUp(s));
        
    }
   

}

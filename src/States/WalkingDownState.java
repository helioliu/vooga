package States;

import sprites.GeneralSprite;
import SpriteAction.WalkDown;




public class WalkingDownState extends EnemyState {
    
    public WalkingDownState(GeneralSprite s) {
        super(s);
        addAction("move down", new WalkDown(s));
        
    }
   

}

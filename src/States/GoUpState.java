package States;

import sprites.GeneralSprite;
import SpriteAction.WalkUp;


import com.golden.gamedev.object.Sprite;

public class GoUpState extends EnemyState {
    
    public GoUpState(GeneralSprite s) {
        super(s);
        addAction("move up", new WalkUp(s));
        
    }
   

}

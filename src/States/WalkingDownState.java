package States;

import sprites.GeneralSprite;
import SpriteAction.WalkDown;




public class WalkingDownState extends State {
    
    public WalkingDownState(GeneralSprite s) {
        super(s);
        addAction("move down" + s.hashCode(), new WalkDown(s));
        
    }
   

}

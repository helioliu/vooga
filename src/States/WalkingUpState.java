package States;

import sprites.GeneralSprite;
import SpriteAction.WalkUp;


public class WalkingUpState extends State {
    
    public WalkingUpState(GeneralSprite s){
        super(s);
        addAction("walk up" + s.hashCode(), new WalkUp(s));
    }
}
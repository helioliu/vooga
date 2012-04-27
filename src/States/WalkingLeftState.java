package States;

import sprites.GeneralSprite;
import SpriteAction.WalkLeft;



public class WalkingLeftState extends State {
    
    public WalkingLeftState(GeneralSprite s){
        super(s);
        addAction("walk left" + s.hashCode(), new WalkLeft(s));
    }
}

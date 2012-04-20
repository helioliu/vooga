package States;

import sprites.GeneralSprite;
import SpriteAction.*;


import com.golden.gamedev.object.Sprite;

public class RegularMotionState extends State {
    
    public RegularMotionState(GeneralSprite s) {
        super(s);
        addAction("Up", new MoveUp(s));
        addAction("Down", new MoveDown(s));
        addAction("Left", new MoveLeft(s));
        addAction("Right", new MoveRight(s));
        addAction("floor collide", new StandAction(s));
        addAction("got hit", new ChangeStatAction(s));
        setMyGravityValue(0);
        
    }
   

}

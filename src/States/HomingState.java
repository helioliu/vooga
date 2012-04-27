package States;

import SpriteAction.Homing;
import SpriteAction.Stationary;
import sprites.GeneralSprite;

public class HomingState extends State {

    public HomingState(GeneralSprite s) {
        super(s);
        addAction("homing", new Homing(s));
    }

}

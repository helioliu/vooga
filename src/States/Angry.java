package States;

import SpriteAction.GetAngry;
import sprites.GeneralSprite;

public class Angry extends State {

    public Angry(GeneralSprite s) {
        super(s);
        addAction("get angry", new GetAngry(s));
    }

}

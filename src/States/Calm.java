package States;

import SpriteAction.GetCalm;
import sprites.GeneralSprite;


public class Calm extends State {

    public Calm(GeneralSprite s) {
        super(s);
        addAction("get calm", new GetCalm(s));
    }
}

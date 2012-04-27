package States;

import sprites.GeneralSprite;
import SpriteAction.Stationary;

import com.golden.gamedev.object.Sprite;

public class StationaryState extends State{

    public StationaryState(GeneralSprite s){
        super(s);
        addAction("stationary", new Stationary(s));
    }

}

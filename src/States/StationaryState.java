package States;

import sprites.GeneralSprite;
import SpriteAction.Stationary;

import com.golden.gamedev.object.Sprite;

public class StationaryState extends EnemyState{

    public StationaryState(GeneralSprite s){
        super(s);
        addAction("set stationary", new Stationary(s));
    }

}

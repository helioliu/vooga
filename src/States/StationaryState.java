package States;

import SpriteAction.Stationary;

import com.golden.gamedev.object.Sprite;

public class StationaryState extends EnemyState{

    public StationaryState(Sprite s){
        super(s);
        myMap.put("set stationary", new Stationary(s));
    }

}

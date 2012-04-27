package States;

import java.util.Map;

import sprites.GeneralSprite;

import com.golden.gamedev.object.Sprite;

import core.EventListener;
import core.EventManager;

public abstract class EnemyState extends State {


    public EnemyState(GeneralSprite s)
    {
        super(s);
    }
    


}

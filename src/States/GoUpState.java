package States;

import SpriteAction.WalkUp;


import com.golden.gamedev.object.Sprite;

public class GoUpState extends EnemyState {
    
    public GoUpState(Sprite s) {
        super(s);
        myMap.put("move up", new WalkUp(s));
        
    }
   

}

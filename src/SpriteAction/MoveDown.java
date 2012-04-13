package SpriteAction;

import com.golden.gamedev.object.Sprite;


public class MoveDown extends SpriteAction {
    public MoveDown(Sprite s) {
        super(s);
 
    }

    public void actionPerformed(Object event)
    {
        mySprite.move(0, 1); 
    }


    
}

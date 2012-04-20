package SpriteAction;

import sprites.GeneralSprite;

public class MoveDown extends SpriteAction {
    public MoveDown(GeneralSprite s) {
        super(s);
 
    }

    public void actionPerformed(Object event)
    {
        mySprite.move(0, 1); 
    }


    
}

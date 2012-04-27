package SpriteAction;

import sprites.GeneralSprite;



public class MoveUp extends SpriteAction {
    public MoveUp(GeneralSprite s) {
        super(s);
    }

    public void actionPerformed(Object event)
    {
        mySprite.move(0, -1); 
    }
    
}

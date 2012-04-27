package SpriteAction;

import sprites.GeneralSprite;

public class WalkLeft extends SpriteAction{

    public WalkLeft(GeneralSprite s) {
        super(s);

    }
    
    public void actionPerformed(Object event){
        mySprite.setMovement(.5, 270);
    }

}

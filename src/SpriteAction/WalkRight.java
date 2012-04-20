package SpriteAction;

import sprites.GeneralSprite;

public class WalkRight extends SpriteAction{

    public WalkRight(GeneralSprite s) {
        super(s);

    }
    
    public void actionPerformed(Object event){
        mySprite.setMovement(.5, 90);
    }

	

}

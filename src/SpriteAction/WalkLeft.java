package SpriteAction;

import sprites.GeneralSprite;

public class WalkLeft extends SpriteAction{

    public WalkLeft(GeneralSprite s) {
        super(s);

    }
    
    public void actionPerformed(String event){
        mySprite.setMovement(.5, 270);
    }

}

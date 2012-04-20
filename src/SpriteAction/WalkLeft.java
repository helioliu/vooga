package SpriteAction;

import com.golden.gamedev.object.Sprite;

import core.EventManager;


public class WalkLeft extends SpriteAction{

    public WalkLeft(Sprite s) {
        super(s);

    }
    
    public void actionPerformed(Object event){
        mySprite.setMovement(.5, 270);
    }

}

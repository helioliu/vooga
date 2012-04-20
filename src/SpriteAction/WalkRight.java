package SpriteAction;

import com.golden.gamedev.object.Sprite;

import core.EventManager;


public class WalkRight extends SpriteAction{

    public WalkRight(Sprite s) {
        super(s);

    }
    
    public void actionPerformed(Object event){
        mySprite.setMovement(.5, 90);
    }

	

}

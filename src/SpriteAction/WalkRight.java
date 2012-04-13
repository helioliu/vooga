package SpriteAction;

import com.golden.gamedev.object.Sprite;

import core.EventManager;


public class WalkRight extends SpriteAction{

    public WalkRight(Sprite s) {
        super(s);
        EventManager em = EventManager.getEventManager();
        em.registerEventListener("walking right", this);
    }
    
    public void actionPerformed(Object event){
        mySprite.setMovement(.025, 90);
    }

	

}

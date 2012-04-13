package SpriteAction;

import com.golden.gamedev.object.Sprite;

import core.EventManager;


public class WalkLeft extends SpriteAction{

    public WalkLeft(Sprite s) {
        super(s);
        EventManager em = EventManager.getEventManager();
        em.registerEventListener("walking left", this);
    }
    
    public void actionPerformed(Object event){
        mySprite.setMovement(.025, 270);
    }

}

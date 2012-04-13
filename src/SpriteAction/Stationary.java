package SpriteAction;

import com.golden.gamedev.object.Sprite;

import core.EventManager;

public class Stationary extends SpriteAction {

    public Stationary(Sprite s) {
        super(s);
        EventManager em = EventManager.getEventManager();
        em.registerEventListener("stay stationary", this);
    }

    public void actionPerformed(Object event)
    {
        mySprite.setMovement(0, 0); 
    }

}

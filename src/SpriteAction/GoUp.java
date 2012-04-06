package SpriteAction;

import com.golden.gamedev.object.Sprite;

import core.EventManager;

public class GoUp extends SpriteAction {
    public GoUp(Sprite s) {
        super(s);
        EventManager em = EventManager.getEventManager();
        em.registerEventListener("going up", this);
    }

    public void actionPerformed(String event)
    {
        mySprite.setMovement(0.025, 0); 
    }
    
}

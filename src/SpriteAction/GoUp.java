package SpriteAction;

import sprites.GeneralSprite;
import core.EventManager;

public class GoUp extends SpriteAction {
    public GoUp(GeneralSprite s) {
        super(s);
        EventManager em = EventManager.getEventManager();
        em.registerEventListener("going up", this);
    }

    public void actionPerformed(String event)
    {
        mySprite.setMovement(0.025, 0); 
    }

    @Override
    public void actionPerformed(Object object) {
        // TODO Auto-generated method stub
        
    }
    
}

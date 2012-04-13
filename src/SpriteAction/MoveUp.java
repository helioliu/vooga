package SpriteAction;

import com.golden.gamedev.object.Sprite;

import core.EventManager;

public class MoveUp extends SpriteAction {
    public MoveUp(Sprite s) {
        super(s);
        EventManager em = EventManager.getEventManager();
//        em.registerEventListener("up", this);
    }

    public void actionPerformed(String event)
    {
        mySprite.move(0, -1); 
    }
    
}

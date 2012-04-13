package SpriteAction;

import com.golden.gamedev.object.Sprite;

import core.EventManager;

public class MoveDown extends SpriteAction {
    public MoveDown(Sprite s) {
        super(s);
        EventManager em = EventManager.getEventManager();
//        em.registerEventListener("down", this);
    }

    public void actionPerformed(String event)
    {
        mySprite.move(0, 1); 
    }
    
}

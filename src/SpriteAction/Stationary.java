package SpriteAction;

import sprites.GeneralSprite;

import com.golden.gamedev.object.Sprite;

import core.EventManager;

public class Stationary extends SpriteAction {

    public Stationary(GeneralSprite s) {
        super(s);
        EventManager em = EventManager.getEventManager();
        em.registerEventListener("stay stationary", this);
    }

    public void actionPerformed(Object event)
    {
        mySprite.setMovement(0, 0); 
    }

}

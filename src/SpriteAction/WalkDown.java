package SpriteAction;

import sprites.GeneralSprite;

public class WalkDown extends SpriteAction {
    public WalkDown(GeneralSprite s) {
        super(s);
    }

    @Override
    public void actionPerformed(Object object) {
        mySprite.setMovement(0.025, 0);
        
    }

    
}

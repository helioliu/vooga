package SpriteAction;

import sprites.GeneralSprite;

public class WalkUp extends SpriteAction {
    public WalkUp(GeneralSprite s) {
        super(s);
    }

    @Override
    public void actionPerformed(String object) {
        mySprite.setMovement(0.025, 0);
        
    }

    
}

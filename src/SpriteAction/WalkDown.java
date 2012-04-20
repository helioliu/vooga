package SpriteAction;

import com.golden.gamedev.object.Sprite;



public class WalkDown extends SpriteAction {
    public WalkDown(Sprite s) {
        super(s);
    }

    @Override
    public void actionPerformed(Object object) {
        mySprite.setMovement(0.025, 0);
        
    }

    
}

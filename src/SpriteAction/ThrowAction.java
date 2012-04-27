package SpriteAction;

import interactiveSprites.InteractiveSprite;
import sprites.GeneralSprite;

public class ThrowAction extends SpriteAction{

    public ThrowAction(GeneralSprite s) {
        super(s);

    }
    
    public void actionPerformed(Object event){
        ((InteractiveSprite) mySprite).throwAction();
    }

	

}

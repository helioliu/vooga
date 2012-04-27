package SpriteAction;

import com.golden.gamedev.object.Timer;

import sprites.BossSprite;
import sprites.GeneralSprite;

public class GetAngry extends SpriteAction{

    public GetAngry(GeneralSprite s) {
        super(s);
    }


    

    @Override
    public void actionPerformed(String s) {
        ((BossSprite)mySprite).setSpeed(2);
        
    }

   
    
    

}

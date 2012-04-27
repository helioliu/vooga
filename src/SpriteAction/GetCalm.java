package SpriteAction;

import com.golden.gamedev.object.Timer;

import sprites.BossSprite;
import sprites.GeneralSprite;

public class GetCalm extends SpriteAction {

    public GetCalm(GeneralSprite s) {
        super(s);
    }


    @Override
    public void actionPerformed(String s) {
        ((BossSprite)mySprite).setPatternTimer(new Timer (2000));         
    }

}

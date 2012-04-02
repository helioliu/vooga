package sprites;

import java.util.List;

import collisions.Hitbox;

import com.golden.gamedev.object.sprite.AdvanceSprite;

public abstract class HitboxSprite extends AdvanceSprite{
    List<Hitbox> myHitboxes;
    
    public List<Hitbox> getHitboxes(){
        return myHitboxes;
    }

}

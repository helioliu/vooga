package sprites;

import java.util.List;

import collisions.Hitbox;

public interface Boxable{
    
    public List<Hitbox> getHitboxes();
    
    //hitboxes should be defined at compile time,
    //so no easy way to add new ones?

}

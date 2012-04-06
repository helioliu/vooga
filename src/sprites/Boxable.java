package sprites;

import java.util.List;

import collisions.Hitbox;

public interface Boxable{
    
    public List<Hitbox> getHitboxes();
    
    public String getDefaultEvent();
    
    //hitboxes should be defined at compile time,
    //so no easy way to add new ones?

}

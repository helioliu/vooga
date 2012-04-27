package sprites;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import collisions.CollisionRect;
import collisions.Hitbox;

public class Flag extends GeneralSprite {
	
	public Flag(BufferedImage i, int x, int y) {
		super(i,x,y);
		myHitboxes = new ArrayList<Hitbox>();
		myHitboxes.add(new Hitbox(new CollisionRect(50,0,290,4), "flag collision"));
		
	}
	
	
}

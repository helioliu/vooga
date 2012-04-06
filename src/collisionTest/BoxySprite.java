package collisionTest;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import sprites.Boxable;

import collisions.CollisionCirc;
import collisions.CollisionRect;
import collisions.CollisionShape;
import collisions.Hitbox;

import com.golden.gamedev.object.sprite.AdvanceSprite;

public class BoxySprite extends AdvanceSprite implements Boxable{
	private List<Hitbox> myHitboxes;
	
	public BoxySprite(BufferedImage[] bi, int x, int y){
		super(bi, x, y);
		myHitboxes = new ArrayList<Hitbox>();
		CollisionShape cs1 = new CollisionRect(0, 0, 64, 64);
		Hitbox hb1 = new Hitbox(cs1, "event");
		myHitboxes.add(hb1);
		CollisionShape cs2 = new CollisionRect(64, 64, 64, 64);
		Hitbox hb2 = new Hitbox(cs2, "22222");
		myHitboxes.add(hb2);
		CollisionShape cs3 = new CollisionCirc(0, 0, 64);
		Hitbox hb3 = new Hitbox(cs3, "ccccc");
		myHitboxes.add(hb3);
	}

	@Override
	public List<Hitbox> getHitboxes() {
		return myHitboxes;
	}

	@Override
	public String getDefaultEvent() {
		return "default";
	}

}

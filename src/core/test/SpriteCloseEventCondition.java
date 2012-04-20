package core.test;

import com.golden.gamedev.object.Sprite;

import core.Condition;

public class SpriteCloseEventCondition implements Condition {

	Sprite sprite1;
	Sprite sprite2;
	
	public SpriteCloseEventCondition(Sprite s1, Sprite s2){
		sprite1 = s1;
		sprite2 = s2;
	}
	
	@Override
	public boolean conditionTrue(Object ... o) {
		if (Math.abs(sprite1.getX() - sprite2.getX()) < 5) {
			System.out.println("X1: "+ sprite1.getX() + " X2: "+sprite2.getX());
			return true;
		}
		return false;
	}

}

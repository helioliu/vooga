package interactiveSprites;

import java.awt.image.BufferedImage;

import com.golden.gamedev.GameObject;

public abstract class Spring_IS extends InteractiveSprite {
	
	public Spring_IS(GameObject owner) {
		super(owner.getImage("MarioSpring.png", false), 0, 0);
	}
	
	public void primaryAction(){
		
				
	}
	
	

}

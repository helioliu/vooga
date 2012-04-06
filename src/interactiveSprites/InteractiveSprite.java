package interactiveSprites;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import com.golden.gamedev.GameObject;
import com.golden.gamedev.object.CollisionManager;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;
import com.golden.gamedev.object.sprite.AdvanceSprite;

public class InteractiveSprite extends Sprite{
	
	private BufferedImage myImage;
	private GameObject currentGO;
	private boolean touching;
	CollisionManager collisionType;
	
	public InteractiveSprite(BufferedImage image, double x, double y) {
			
	}
	
	public void initResources() {
	}
	
	public void update(long elapsedTime){
		
		if(currentGO.keyDown(KeyEvent.VK_LEFT) && touching){
			//this.setHorizontalSpeed(new ISpriteToHero);
		}
		
		if(currentGO.keyDown(KeyEvent.VK_RIGHT) && touching){
			//this.setHorizontalSpeed()
		}
		
		if(currentGO.keyDown(KeyEvent.VK_UP) && touching){
			//this.setVerticleSpeed()
		}
		
		if(currentGO.keyDown(KeyEvent.VK_DOWN) && touching){
			//this.setVerticleSpeed()
		}
		
		
	}
		
		
		
}
	
	



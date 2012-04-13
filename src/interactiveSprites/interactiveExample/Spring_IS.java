package interactiveSprites.interactiveExample;

import java.awt.image.BufferedImage;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.Timer;
import com.golden.gamedev.object.collision.CollisionGroup;

public class Spring_IS extends Sprite implements InteractiveSprite {

	String myType;
	RPGGame myGame;
	
	public Spring_IS(BufferedImage bufferedImage, int i, int j, RPGGame game) {
		super(bufferedImage, i, j);
		myType = "spring";
		myGame = game;
	}
	
	public void primaryAction(CollisionGroup c) {
		
		if(c.getCollisionSide()== c.BOTTOM_TOP_COLLISION) {
		myGame.BIGCAT_GROUP.getActiveSprite().setVerticalSpeed(-.25);
		}
	}
	
	public void userMove() {
		this.setX(myGame.BIGCAT_GROUP.getActiveSprite().getX() + this.width);
		this.setY(myGame.BIGCAT_GROUP.getActiveSprite().getY());
	}
	
	public String getType() {
		return myType;
	}
}

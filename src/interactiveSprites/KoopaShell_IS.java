package interactiveSprites;
import game.Platformer;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import sprites.GeneralSprite;
import sprites.LevelEditable;
import stateManagers.StateManager;
import States.StationaryState;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.CollisionGroup;

public class KoopaShell_IS extends InteractiveSprite implements LevelEditable {
	
	
	public KoopaShell_IS() {
		super();
		myType = "koopa shell";
		getStateManager().addState(new StationaryState(this));
		//myGame.INTERACTIVE_SPRITES.add(this);
	}
	
	
	public void primaryAction(CollisionGroup c, GeneralSprite s) {
		if(c.getCollisionSide()== c.BOTTOM_TOP_COLLISION) {
		}
		if(c.getCollisionSide()== c.TOP_BOTTOM_COLLISION) {
		}
		if(c.getCollisionSide()== c.LEFT_RIGHT_COLLISION) {
		}
		if(c.getCollisionSide()== c.RIGHT_LEFT_COLLISION) {
		}
	}
	
	public void throwAction() {
		this.setHorizontalSpeed(.3);
	}

}
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

/**
 * Specific implementation for KoopaShell interactive sprite
 * @author Sam
 */

public class KoopaShell_IS extends InteractiveSprite implements LevelEditable {
	
	/**
	 * Creates the specific sprite in stationary state and identifies the type
	 * @param game The game currently in use
	 */
	public KoopaShell_IS() {
		super();
		myType = "koopa shell";
		getStateManager().addState(new StationaryState(this));
	}
	
	/**
	 * Defines the primary action for the sprite upon collision. Different actions can be defined
	 * for each potential side of collision
	 */
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
	
	/**
	 * Defines specific action once object is thrown
	 */
	public void throwAction() {
		this.setHorizontalSpeed(.3);
	}

}
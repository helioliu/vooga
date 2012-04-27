package interactiveSprites;
import game.Platformer;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import sprites.GeneralSprite;
import sprites.LevelEditable;
import stateTransitions.ChangeStateTransition;
import stateTransitions.StateTransition;
import States.StationaryState;
import States.InteractiveSpriteStates.CarryingState;
import States.InteractiveSpriteStates.TouchingState;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.CollisionGroup;
/**
 * Specific implementation for Spring interactive sprite
 * @author Sam
 */

@SuppressWarnings("serial")
public class Spring_IS extends InteractiveSprite implements LevelEditable {
	
	/**
	 * Creates the specific sprite in stationary state and identifies the type	
	 * @param game The game currently in use
	 */
	public Spring_IS(Platformer game) {
		
		super(game);
		myType = "spring";
		getStateManager().addState((new StationaryState(this)));
		//myGame.INTERACTIVE_SPRITES.add(this);
	}
	
	/**
	 * Defines the primary action for the sprite upon collision. Different actions can be defined
	 * for each potential side of collision
	 */
	public void primaryAction(CollisionGroup c, GeneralSprite s) {
		
		if(c.getCollisionSide()== c.BOTTOM_TOP_COLLISION) {
			s.setVerticalSpeed(-.25);			
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
	public void throwAction(){
		getStateManager().changeState(new StationaryState(this));
	}
	

}

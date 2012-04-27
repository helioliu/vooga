package interactiveSprites;

import sprites.GeneralSprite;
import stateManagers.StateManager;
import stateTransitions.ChangeStateTransition;
import stateTransitions.StateTransition;
import States.InteractiveSpriteStates.TouchingState;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.AdvanceCollisionGroup;

import core.EventManager;

/**
 * Creates a CollisionGroup specifically for interactiveSprites and the hero character
 * @author Sam
 */
public class InteractiveSpriteCollision extends AdvanceCollisionGroup {
	
	StateManager myStateManager;
	ChangeStateTransition myCST;
	
	public InteractiveSpriteCollision(){
		pixelPerfectCollision = true;
	}

	/**
	 * Sets the state of the interactive sprite to a "touching" state. Reverts position of hero
	 * character to not allow intersection. Fires primaryAction of interactiveSprite
	 * @param s1 The sprite from the first sprite group
	 * @param s2 The sprite from the second sprite group
	 */
	@Override
	public void collided(Sprite s1, Sprite s2) {
		
		EventManager.getEventManager().sendEvent("ISCollision");
		s1.setLocation(s1.getOldX(), s1.getOldY());
		((InteractiveSprite) s2).primaryAction(this,((GeneralSprite) s1));
	}
	

}

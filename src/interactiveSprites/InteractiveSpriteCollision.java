package interactiveSprites;

import sprites.GeneralSprite;
import stateManagers.StateManager;
import stateTransitions.ChangeStateTransition;
import stateTransitions.StateTransition;
import States.InteractiveSpriteStates.TouchingState;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.AdvanceCollisionGroup;

import core.EventManager;

public class InteractiveSpriteCollision extends AdvanceCollisionGroup {
	
	StateManager myStateManager;
	ChangeStateTransition myCST;

	@Override
	public void collided(Sprite s1, Sprite s2) {
		
		EventManager.getEventManager().sendEvent("ISCollision");
		((InteractiveSprite) s2).primaryAction(this,((GeneralSprite) s1));
	}

}

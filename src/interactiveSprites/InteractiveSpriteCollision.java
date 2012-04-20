package interactiveSprites;

import stateManagers.StateManager;
import stateTransitions.ChangeStateTransition;
import stateTransitions.StateTransition;

import States.RegularMotionState;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.AdvanceCollisionGroup;

public class InteractiveSpriteCollision extends AdvanceCollisionGroup {
	
	StateManager myStateManager;
	ChangeStateTransition myCST;

	@Override
	public void collided(Sprite s1, Sprite s2) {
StateTransition reverse = new ChangeStateTransition(((GeneralSprite) s2).getStateManager(), "switchstates", new RegularMotionState(this));
		
		reverse.activate();
	}

}

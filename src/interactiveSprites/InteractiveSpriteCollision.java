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
	
	public InteractiveSpriteCollision(){
		pixelPerfectCollision = true;
	}

	@Override
	public void collided(Sprite s1, Sprite s2) {
		
		EventManager.getEventManager().sendEvent("ISCollision");
		s1.setLocation(s1.getOldX(), s1.getOldY());
		((InteractiveSprite) s2).primaryAction(this,((GeneralSprite) s1));
		
//		if(getCollisionSide()== BOTTOM_TOP_COLLISION) BottomTopCollided(s1, s2);
//		
//		if(getCollisionSide()== TOP_BOTTOM_COLLISION) TopBottomCollided(s1, s2);
//			
//		if(getCollisionSide()== LEFT_RIGHT_COLLISION) LeftRightCollided(s1, s2);			
//		
//		if(getCollisionSide()== RIGHT_LEFT_COLLISION) RightLeftCollided(s1, s2);
	}
	
//	public abstract void RightLeftCollided(Sprite s1, Sprite s2);
//	
//	public abstract void LeftRightCollided(Sprite s1, Sprite s2);
//	
//	public abstract void TopBottomCollided(Sprite s1, Sprite s2);
//	
//	public abstract void BottomTopCollided(Sprite s1, Sprite s2);

}

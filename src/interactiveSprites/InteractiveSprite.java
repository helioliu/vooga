package interactiveSprites;

import game.Platformer;
import sprites.GeneralSprite;
import sprites.StateSprite;
import stateTransitions.ChangeStateTransition;
import stateTransitions.StateTransition;
import States.InteractiveSpriteStates.CarryingState;
import States.InteractiveSpriteStates.TouchingState;
import com.golden.gamedev.object.collision.CollisionGroup;

@SuppressWarnings("serial")
public abstract class InteractiveSprite extends StateSprite {
	
	String path;
	String myType;
	Platformer myGame;
	
	public InteractiveSprite(Platformer game) {
		myGame = game;
		
		StateTransition collide = new ChangeStateTransition(getStateManager(), "ISCollision", new TouchingState((this)));
		collide.activate();
		
		StateTransition carrying = new ChangeStateTransition(getStateManager(),"toCarrying", new CarryingState(this));
		carrying.activate();
	}
	
	public String getType() {
		return myType;
	}
	
	public abstract void primaryAction(CollisionGroup c, GeneralSprite s);
	
	public abstract void throwAction();
	
	
	

}

package sprites;

import game.Platformer;
import stateTransitions.ChangeStateTransition;
import stateTransitions.StateTransition;
import States.InteractiveSpriteStates.CarryingState;
import States.InteractiveSpriteStates.TouchingState;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.collision.CollisionGroup;

/**
 * Creates a general framework for interactiveSprites. Provides basic functionality
 * for carrying a dropping. More meaningful functionality should be defines in
 * the subclasses extending this one 
 * @author Sam
 */


@SuppressWarnings("serial")
public abstract class InteractiveSprite extends StateSprite {
	
	String path;
	String myType;
	Game myGame;
	
	/**
	 * This constructor calls state transitions that enable the object to be carried. These states identify
	 * when objects are colliding and moves them to carrying state once the appropriate key is pressed
	 */
	
	public InteractiveSprite() {
		
		StateTransition collide = new ChangeStateTransition(getStateManager(), "ISCollision", new TouchingState((this)));
		collide.activate();
		
		StateTransition carrying = new ChangeStateTransition(getStateManager(),"toCarrying", new CarryingState(this));
		carrying.activate();
	}
	
	/**
	 * @return The type of the interactiveSprite
	 */
	
	public String getType() {
		return myType;
	}
	
	/**
	 * Defines the primary action the should occur upon collision
	 * @param c The CollisionGroup that contains the sprites
	 * @param s The sprite whose attribute that you want to manipulate
	 */
	
	public abstract void primaryAction(CollisionGroup c, GeneralSprite s);
	
	/**
	 * Defines how an object should be released after being carried.
	 */
	
	public abstract void throwAction();
	
	
	

}

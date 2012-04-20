package sprites;

import stateTransitions.ChangeStateTransition;
import States.RegularMotionState;
import States.WalkingRightState;

public class WalkingBadGuy extends Enemy {
    
    public WalkingBadGuy(){
        super();
        myStateManager.getTransitions().add(new ChangeStateTransition(myStateManager, "walk right", new WalkingRightState(this)));
    }
}

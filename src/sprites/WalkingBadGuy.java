package sprites;

import stateTransitions.ChangeStateTransition;
import stateTransitions.StateTransition;
import States.RegularMotionState;
import States.WalkingLeftState;
import States.WalkingRightState;

public class WalkingBadGuy extends Enemy {

    public WalkingBadGuy(){
        super();
        StateTransition one = new ChangeStateTransition(getStateManager(), "walk right", new WalkingRightState(this));
        StateTransition two = new ChangeStateTransition(getStateManager(), "walk left", new WalkingLeftState(this));
        one.activate();
        two.activate();
    }
}

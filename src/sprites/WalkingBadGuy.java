package sprites;

import stateTransitions.ChangeStateTransition;
import stateTransitions.StateTransition;
import States.RegularMotionState;
import States.WalkingDownState;
import States.WalkingLeftState;
import States.WalkingRightState;
import States.WalkingUpState;

public class WalkingBadGuy extends Enemy {

    public WalkingBadGuy(){
        super();
        StateTransition one = new ChangeStateTransition(getStateManager(), "walk right", new WalkingRightState(this));
        StateTransition two = new ChangeStateTransition(getStateManager(), "walk left", new WalkingLeftState(this));
        StateTransition three = new ChangeStateTransition(getStateManager(), "walk up", new WalkingUpState(this));
        StateTransition four = new ChangeStateTransition(getStateManager(), "walk down", new WalkingDownState(this));
        one.activate();
        two.activate();
        three.activate();
        four.activate();
    }
}

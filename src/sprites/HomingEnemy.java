package sprites;



import stateTransitions.ChangeStateTransition;
import stateTransitions.StateTransition;
import States.HomingState;
import States.StationaryState;

public class HomingEnemy extends StateSprite {

    private GeneralSprite myTarget;
    
    public HomingEnemy(GeneralSprite target){
        myTarget = target;
        String tag = ""+this.hashCode();
        StateTransition one = new ChangeStateTransition(getStateManager(), "homing" + tag, new HomingState(this));
        StateTransition two = new ChangeStateTransition(getStateManager(), "stationary" + tag, new StationaryState(this));
        one.activate();
        two.activate();
    }

    public GeneralSprite getMyTarget() {
        return myTarget;
    }

    
    
    
}

package sprites;



import stateTransitions.ChangeStateTransition;
import stateTransitions.StateTransition;
import States.HomingState;
import States.StationaryState;

public class HomingEnemy extends StateSprite {

    private GeneralSprite myTarget;
    
    //MAKE SURE YOU SET THE TARGET. It doesn't in the constructor for the sake of the level editor
    public HomingEnemy(){
        String tag = ""+this.hashCode();
        StateTransition one = new ChangeStateTransition(getStateManager(), "homing" + tag, new HomingState(this));
        StateTransition two = new ChangeStateTransition(getStateManager(), "stationary" + tag, new StationaryState(this));
        one.activate();
        two.activate();
    }

    public GeneralSprite getMyTarget() {
        return myTarget;
    }
    
    public void setMyTarget(GeneralSprite target){
        myTarget = target;
    }

    
    
    
}

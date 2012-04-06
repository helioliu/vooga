package StateMachines;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import Enemies.Enemy;
import States.GoUpState;
import States.State;
import States.StationaryState;
import States.WalkingLeftState;
import States.WalkingRightState;

import com.golden.gamedev.object.Sprite;

public class EnemyStateMachine extends StateMachine {

    public EnemyStateMachine(Enemy enemy){
        possibleStates = new ArrayList<State>();
   //     possibleStates.add(new WalkingLeftState(enemy));
   //    possibleStates.add(new WalkingRightState(enemy));
   //     possibleStates.add(new StationaryState(enemy));
   //     possibleStates.add(new GoUpState(enemy));
    }
    
    public void add(State state){
        possibleStates.add(state);
    }

    public void actionPerformed(String eventName) {


    }



}

package stateTransitions;

import States.OnLandState;
import States.RegularMotionState;
import States.State;

import com.golden.gamedev.object.Sprite;

import sprites.Chris_TestSprite;
import sprites.GeneralSprite;
import stateManagers.StateManager;

public class TestTransition {

   public static void main(String[] args){
       GeneralSprite s = new Chris_TestSprite();
       StateManager manager = new StateManager(s, new OnLandState(s));
       State s1 = new RegularMotionState(s);
       State s2 = new OnLandState(s);
       System.out.println(manager.isCurrentlyActive(s1));
       System.out.println(manager.isCurrentlyActive(s2));
       
       //StateTransition st = new ChangeStateTransition(manager, "hello", new OnLandState(s) );
       //System.out.println(st);
       
   }

}

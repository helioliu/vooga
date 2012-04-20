package stateTransitions;

import States.OnLandState;

import com.golden.gamedev.object.Sprite;

import stateManagers.StateManager;

public class TestTransition {

   public static void main(String[] args){
       Sprite s = new Sprite();
       StateManager manager = new StateManager(s, null);
       
       StateTransition st = new ChangeStateTransition(manager, "hello", new OnLandState(s) );
       System.out.println(st);
       
   }

}

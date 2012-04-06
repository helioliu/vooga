package StateMachines;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import States.OnLandState;
import States.State;

public class CharacterStateMachine extends StateMachine{

	public CharacterStateMachine()
	{
		possibleStates = new ArrayList<State>();
		possibleStates.add(new OnLandState(/* some sprite*/));
	}
	
	@Override
	public void perform(ActionEvent ae) {
		// TODO Auto-generated method stub
		
	}

}

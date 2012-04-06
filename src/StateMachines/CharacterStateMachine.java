package StateMachines;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import com.golden.gamedev.object.Sprite;

import States.OnLandState;
import States.State;

public class CharacterStateMachine extends StateMachine{

	public CharacterStateMachine(Sprite character)
	{
		possibleStates = new ArrayList<State>();
		possibleStates.add(new OnLandState(character));
	}
	
	

	@Override
	public void actionPerformed(String eventName) {
		
		
	}


}

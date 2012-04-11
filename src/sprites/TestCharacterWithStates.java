package sprites;

import hudDisplay.HeadsUpDisplay;

import com.golden.gamedev.object.Sprite;

import StateMachines.CharacterStateMachine;
import StateMachines.StateMachine;

public class TestCharacterWithStates extends Sprite{
	private StateMachine stateManager;
	private HeadsUpDisplay HUD;
	
	public HeadsUpDisplay getHUD(){
		return HUD;
	}
	
	public void setHUD(HeadsUpDisplay HUD){
		this.HUD = HUD;
	}
	
	public TestCharacterWithStates()
	{
		super();
		stateManager = new CharacterStateMachine(((Sprite) this));
		
	}
	
	public void update(long elapsedTime)
	{
		
	}
	public void detectKeyBoard(long elapsedTime)
	{
		
	}
	

}

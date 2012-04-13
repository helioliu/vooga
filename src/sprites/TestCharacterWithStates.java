package sprites;
import java.util.HashMap;
import com.golden.gamedev.object.Sprite;
import StateMachines.CharacterStateMachine;
import StateMachines.StateMachine;

public class TestCharacterWithStates extends Sprite{
	private StateMachine stateManager;
	private HashMap<String, Integer> myScores;
	
	
	
	public TestCharacterWithStates()
	{
		super();
		myScores = new HashMap<String, Integer>();
		stateManager = new CharacterStateMachine(((Sprite) this));
		
	}
	
	public void createScore(String name, Integer score) {
		myScores.put(name, score);
	}
	
	public Integer getScore(String name){
		return myScores.get(name);
	}
	
	public HashMap<String, Integer> getInternalScores(){
		return myScores;
	}
	
	public void changeScore(String name, int x){	
		if(myScores.get(name) + x > 0)
		createScore(name,myScores.get(name) + x);
		else
			myScores.put(name, 0);
		
	}
	public void update(long elapsedTime)
	{
		
	}
	public void detectKeyBoard(long elapsedTime)
	{
		
	}
	

}

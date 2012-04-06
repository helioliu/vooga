package cutscenes;

import java.util.ArrayList;
import java.util.List;

import core.EventManager;

public class Cutscene {
	List<SpriteAutomation> myAutomations;
	
	public Cutscene() {
		myAutomations = new ArrayList<SpriteAutomation>();
		init();
	}
	
	public void addSpriteAutomation(SpriteAutomation automation) {
		myAutomations.add(automation);
	}
	
	public void addSpriteAutomationList(List<SpriteAutomation> automation) {
		myAutomations.addAll(automation);
	}
	
	public void init() {
		EventManager.getEventManager().sendEvent("cutscene-start");
	}
	
	public void update(long timeElapsed) {
		for(SpriteAutomation automation : myAutomations) {
			automation.update(timeElapsed);
		}
		
	}
}

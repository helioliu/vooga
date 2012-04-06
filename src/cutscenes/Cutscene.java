package cutscenes;

import java.util.ArrayList;
import java.util.List;

import core.EventManager;
import core.EventListener;

public class Cutscene implements EventListener {
	List<SpriteAutomation> myAutomations;
	private boolean cutsceneOn;
	
	public Cutscene() {
		myAutomations = new ArrayList<SpriteAutomation>();
		cutsceneOn = false;
		EventManager.getEventManager().registerEventListener("cutscene-begin", this);
	}
	
	public void addSpriteAutomation(SpriteAutomation automation) {
		myAutomations.add(automation);
	}
	
	public void addSpriteAutomationList(List<SpriteAutomation> automation) {
		myAutomations.addAll(automation);
	}
	
	public void update(long timeElapsed) {
		if(cutsceneOn) {
			cutsceneOn = false;
			for(SpriteAutomation automation : myAutomations) {
				automation.update(timeElapsed);
				// If any of the automations are still working, don't quit.
				cutsceneOn = cutsceneOn | automation.isCurrentlyAnimating();
			}
		}
		if(!cutsceneOn) {
			EventManager.getEventManager().sendEvent("cutscene-end");
		}
		
	}

	@Override
	public void actionPerformed(String eventName) {
		if (eventName.equals("cutscene-begin")) {
			cutsceneOn = true;
		}
	}
}

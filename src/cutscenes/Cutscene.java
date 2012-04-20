package cutscenes;

import java.io.FileNotFoundException;

import core.Condition;
import core.EventListener;
import core.EventManager;

public class Cutscene implements EventListener {
	EventAutomation currentAutomation;
	String beginEvent;
	String endEvent;
	
	public Cutscene(EventAutomation StartAutomation, String beginEvent, String endEvent) {
		currentAutomation = StartAutomation;
		this.beginEvent = beginEvent;
		this.endEvent = endEvent;
		EventManager.getEventManager().registerEventListener(beginEvent, this);
		EventManager.getEventManager().registerEventListener(endEvent, this);
	}
	
	public void update(long timeElapsed) {
		for(Condition cond : currentAutomation.getTransitions().keySet()) {
			if(cond.conditionTrue()) {
				currentAutomation.endAutomation();
				currentAutomation = currentAutomation.getTransitions().get(cond);
				currentAutomation.beginAutomation();
			}
		}
	}
	
	public void endCutscene() {
		
	}

	public void actionPerformed(Object object) {
		String event = (String) object;
		if(event.equals(beginEvent)) {
			currentAutomation.beginAutomation();
		}
		if(event.equals(endEvent)) {
			currentAutomation.endAutomation();
		}
	}
}

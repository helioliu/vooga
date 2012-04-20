package cutscenes;

import input.InputManager;

import java.io.FileNotFoundException;

import core.Condition;
import core.EventListener;
import core.EventManager;

public class Cutscene implements EventListener {
	EventAutomation currentAutomation;
	String beginEvent;
	String endEvent;
	boolean active;
	
	public Cutscene(EventAutomation StartAutomation, String beginEvent, String endEvent) {
		currentAutomation = StartAutomation;
		this.beginEvent = beginEvent;
		this.endEvent = endEvent;
		active = false;
		EventManager.getEventManager().registerEventListener(beginEvent, this);
		EventManager.getEventManager().registerEventListener(endEvent, this);
	}
	
	public void update(long timeElapsed) {
		if (active) {
			for(Condition cond : currentAutomation.getTransitions().keySet()) {
				if(cond.conditionTrue()) {
					currentAutomation.endAutomation();
					currentAutomation = currentAutomation.getTransitions().get(cond);
					currentAutomation.beginAutomation();
				}
			}
		}
	}
	
	public void beginCutscene() {
		currentAutomation.beginAutomation();
		active = true;
		InputManager.setActive(false);
	}
	public void endCutscene() {
		currentAutomation.endAutomation();
		active = false;
		InputManager.setActive(true);
	}

	public void actionPerformed(Object object) {
		String event = (String) object;
		if(event.equals(beginEvent) & !active) {
			beginCutscene();
		}
		if(event.equals(endEvent)) {
			endCutscene();
		}
	}
}

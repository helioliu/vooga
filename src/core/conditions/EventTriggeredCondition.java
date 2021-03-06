package core.conditions;

import core.Condition;
import core.EventListener;
import core.EventManager;

public class EventTriggeredCondition implements Condition,EventListener{
	private boolean active = false;
	
	public EventTriggeredCondition(String triggerEvent) {
		EventManager.getEventManager().registerEventListener(triggerEvent, this);
	}


	public void actionPerformed(String s) {
		if (!active) {
			active = true;
		}
	}


	public boolean conditionTrue() {
		if(active) {
			active = false;
			return true;
		}
		return false;
	}


	public void reset() {
		active = false;
	}

}

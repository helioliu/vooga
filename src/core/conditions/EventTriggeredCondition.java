package core.conditions;

import core.Condition;
import core.EventListener;
import core.EventManager;

public class EventTriggeredCondition implements Condition,EventListener{
	private boolean active = false;
	
	public EventTriggeredCondition(String triggerEvent) {
		EventManager.getEventManager().registerEventListener(triggerEvent, this);
	}

<<<<<<< HEAD

	public void actionPerformed(Object object) {
=======
	@Override
	public void actionPerformed(String s) {
>>>>>>> 7c99eb137ec15ab482536ea0509c63d2c8797ca0
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

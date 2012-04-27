package core.conditions;

import com.golden.gamedev.object.Timer;

import core.Condition;
import core.EventManager;

public class DelayedCondition implements Condition {
	private boolean activated;
	private Timer countdown;
	
	public DelayedCondition(int count) {
		activated = false;
		countdown = new Timer(count);
	}

	public boolean conditionTrue() {
		update();
		if(activated) {
			activated = false;
			return true;
		}
		return false;
	}
	
	private void update() {
		long timeElapsed = EventManager.getEventManager().getElapsedTime();
		if(countdown.action(timeElapsed)) {
			activated = true;
			countdown.setActive(false);
		}
	}

	public void reset() {
		countdown.refresh();
	}

}

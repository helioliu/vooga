package core.conditions;

import com.golden.gamedev.object.Timer;

import core.EventCondition;
import core.EventManager;

public class TimedCutsceneCondition implements EventCondition {
	private boolean activated;
	private Timer countdown;
	private Timer duration;
	private int durTime;
	
	public TimedCutsceneCondition(int count, int dur) {
		activated = false;
		countdown = new Timer(count);
		durTime = dur;
	}

	public boolean conditionTrue(Object... o) {
		update();
		return activated;
	}
	
	private void update() {
		long timeElapsed = EventManager.getEventManager().getElapsedTime();
		if(countdown.action(timeElapsed)) {
			activated = true;
			countdown.setActive(false);
			duration = new Timer(durTime);
		}
		if(duration.action(timeElapsed)) {
			activated = false;
			duration.setActive(false);
		}
	}
}

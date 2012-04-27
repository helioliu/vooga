package core.test;

import com.golden.gamedev.object.Timer;

import core.Condition;
import core.EventManager;

public class TimerEventCondition implements Condition{

	private Timer timer;
	private boolean timeOver;
	
	public TimerEventCondition(Timer t){
		timer = t;
		timeOver = false;
	}
	
	public boolean conditionTrue(Object ... obj) {
		if(timer.action(EventManager.getEventManager().getElapsedTime())){
			timeOver = true;
		}
		return !timeOver;
	}

}

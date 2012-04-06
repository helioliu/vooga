package cutscenes;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.Timer;

import core.EventManager;

public class SpriteAutomation {
	Sprite sprite;
	List<AutomatedAction> actionList;
	Timer currentTimer;
	Integer currentPosition;

	public SpriteAutomation(Sprite s, String filePath) {
		File f = new File(filePath);
		sprite = s;
		actionList = parseAutomation(f);
		currentPosition = 0;
		currentTimer = new Timer(actionList.get(0).getDuration());

	}

	public List<AutomatedAction> parseAutomation(File f) {
		List<AutomatedAction> automation = new ArrayList<AutomatedAction>();
		// TODO do this
		return automation;
	}

	public void update(long timeElapsed) {
		if(currentTimer.action(timeElapsed)){
			EventManager.getEventManager().sendEvent(actionList.get(currentPosition).getEventName());
			currentPosition ++;
			checkIfDone();
			currentTimer = new Timer(actionList.get(currentPosition).getDuration());
		}
		
	}
	
	private void checkIfDone() {
		if(currentPosition >= actionList.size()) {
			// TODO do this
		}
	}
}

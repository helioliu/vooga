package cutscenes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.*;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.Timer;

import core.EventManager;

public class SpriteAutomation {
	Sprite sprite;
	List<AutomatedAction> actionList;
	Timer currentTimer;
	Integer currentPosition;
	boolean currentlyAnimating;

	public SpriteAutomation(Sprite s, String filePath) throws FileNotFoundException, BadFileFormatException {
		File f = new File(filePath);
		sprite = s;
		actionList = parseAutomation(f);
		currentPosition = 0;
		currentTimer = new Timer(0);
		currentlyAnimating = true;

	}

	public List<AutomatedAction> parseAutomation(File f) throws FileNotFoundException, BadFileFormatException {
		List<AutomatedAction> automation = new ArrayList<AutomatedAction>();
		Scanner s = new Scanner(f);

		while (s.hasNext()) {
			String l = s.nextLine();
			String[] line = l.split(":");
			if (line.length != 2)
				throw new BadFileFormatException("Cannot read line: "+l);
			String eventName = line[0];
			Integer duration = Integer.parseInt(line[1]);
//			System.out.println(eventName+" "+Integer.toString(duration));
			AutomatedAction current = new AutomatedAction(eventName, duration);
			automation.add(current);
		}

		return automation;
	}

	public void update(long timeElapsed) {
		
		if (currentTimer.action(timeElapsed) & currentlyAnimating) {
			EventManager.getEventManager().sendEvent(
					actionList.get(currentPosition).getEventName());
			currentTimer = new Timer(actionList.get(currentPosition)
					.getDuration());
			currentPosition++;
			checkIfDone();
			
		}

	}

	private void checkIfDone() {
		if (currentPosition >= actionList.size()) {
			currentlyAnimating = false;
		}
	}

	public boolean isCurrentlyAnimating() {
		return currentlyAnimating;
	}
	
	public List<AutomatedAction> getActionList() {
		return actionList;
	}

}

package cutscenes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.golden.gamedev.object.Timer;

import core.EventCondition;
import core.EventListener;
import core.EventManager;
import core.conditions.DelayedCondition;
import core.conditions.TimedCutsceneCondition;

public class Cutscene implements EventListener {
	Map<EventCondition, String> myAutomations;
	private File automationScript;
	private int myDuration;
	private Timer durationCountdown;

	public Cutscene(String filepath, int duration) {
		automationScript = new File(filepath);
		myDuration = duration;
		EventManager.getEventManager().registerEventListener("cutscene-begin",
				this);

	}

	public Map<EventCondition, String> parseAutomations(File f)
			throws FileNotFoundException, BadFileFormatException {
		Map<EventCondition, String> automations = new HashMap<EventCondition, String>();
		Scanner s = new Scanner(f);

		while (s.hasNext()) {
			String l = s.nextLine();
			String[] line = l.split(":");
			if (!(line.length == 2 | line.length == 3))
				throw new BadFileFormatException("Cannot read line: " + l);
			String eventName = line[0];
			Integer occursAt = Integer.parseInt(line[1]);
			EventCondition current = null;
			if (line.length == 3) {
				Integer duration = Integer.parseInt(line[2]);
				current = new TimedCutsceneCondition(occursAt,
						duration);
			} else {
				current = new DelayedCondition(occursAt);
			}
			
			automations.put(current, eventName);
		}

		return automations;
	}

	public void update(long timeElapsed) {
		if(durationCountdown != null) {
			if (durationCountdown.action(timeElapsed)) {
				EventManager.getEventManager().sendEvent("cutscene-end");
				endCutscene();
				durationCountdown = null;
			}
		}
	}

	private void beginCutscene() throws FileNotFoundException,
			BadFileFormatException {
		durationCountdown = new Timer(myDuration);
		myAutomations = parseAutomations(automationScript);
		for (EventCondition condition : myAutomations.keySet()) {
			EventManager.getEventManager().addEventCondition(condition,
					myAutomations.get(condition));
		}
	}

	private void endCutscene() {
		for (EventCondition condition : myAutomations.keySet()) {
			EventManager.getEventManager().removeEventCondition(condition);
		}
	}
	
	@Override
	public void actionPerformed(String eventName) {
		if (eventName.equals("cutscene-begin")) {
			try {
				beginCutscene();
			} catch (FileNotFoundException | BadFileFormatException e) {
				System.err.println("Cannot read automation script");
			}
		}
	}
}

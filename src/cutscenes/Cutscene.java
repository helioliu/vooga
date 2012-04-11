package cutscenes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import core.EventCondition;
import core.EventListener;
import core.EventManager;
import core.conditions.TimedCutsceneCondition;

public class Cutscene implements EventListener {
	Map<EventCondition, String> myAutomations;
	// private boolean cutsceneOn;
	private File automationScript;

	public Cutscene(String filepath) {
		automationScript = new File(filepath);
		// cutsceneOn = false;
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
			if (line.length != 2 | line.length != 3)
				throw new BadFileFormatException("Cannot read line: " + l);
			String eventName = line[0];
			Integer occursAt = Integer.parseInt(line[1]);
			Integer duration = 1;
			if (line.length == 3)
				duration = Integer.parseInt(line[2]);
			EventCondition current = new TimedCutsceneCondition(occursAt,
					duration);
			automations.put(current, eventName);
		}

		return automations;
	}

	public void update(long timeElapsed) {
		// if(cutsceneOn) {
		// cutsceneOn = false;
		// for(SpriteAutomation automation : myAutomations) {
		// automation.update(timeElapsed);
		// // If any of the automations are still working, don't quit.
		// cutsceneOn = cutsceneOn | automation.isCurrentlyAnimating();
		// }
		// }
		// if(!cutsceneOn) {
		// EventManager.getEventManager().sendEvent("cutscene-end");
		// }

	}

	private void beginCutscene() throws FileNotFoundException,
			BadFileFormatException {
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
		if (eventName.equals("cutscene-end")) {
			endCutscene();
		}
	}
}

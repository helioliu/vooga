package cutscenes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import core.Condition;
import core.conditions.DelayedCondition;
import core.conditions.TimedCutsceneCondition;

public abstract class EventAutomation {
	protected Map<Condition, EventAutomation> myTransitions;
	
	public Map<Condition, EventAutomation> getTransitions() {
		return myTransitions;
	}
	
	public void addTransition(Condition condition, EventAutomation automation) {
		myTransitions.put(condition, automation);
	}
	
	
	
	protected static Map<Condition, String> parseAutomations(File f)
			throws FileNotFoundException, BadFileFormatException {
		Map<Condition, String> automations = new HashMap<Condition, String>();
		Scanner s = new Scanner(f);

		while (s.hasNext()) {
			String l = s.nextLine();
			String[] line = l.split(":");
			if (!(line.length == 2 | line.length == 3))
				throw new BadFileFormatException("Cannot read line: " + l);
			String eventName = line[0];
			Integer occursAt = Integer.parseInt(line[1]);
			Condition current = null;
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
	
	public abstract void update(long timeElapsed);
	public abstract void beginAutomation();
	public abstract void endAutomation();
}

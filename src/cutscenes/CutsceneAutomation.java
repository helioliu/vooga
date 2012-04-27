package cutscenes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.golden.gamedev.object.Timer;

import core.Condition;
import core.EventListener;
import core.EventManager;
import core.conditions.DelayedCondition;
import core.conditions.TimedCutsceneCondition;

public class CutsceneAutomation extends EventAutomation implements
		EventListener {
	private File automationScript;

	public CutsceneAutomation(String filepath) {
		automationScript = new File(filepath);
		myAutomations = parseAutomations(automationScript);
		myTransitions = new HashMap<Condition, EventAutomation>();
	}
	
	public CutsceneAutomation() {
		myAutomations = new HashMap<Condition, String>();
		myTransitions = new HashMap<Condition, EventAutomation>();
	}


	public void beginAutomation() {
		for (Condition condition : myTransitions.keySet()) {
			condition.reset();
		}
		for (Condition condition : myAutomations.keySet()) {
			condition.reset();
//			System.out.println(condition+" "+myAutomations.get(condition));
			EventManager.getEventManager().addEventCondition(condition,
					myAutomations.get(condition));
		}
	}

	public void endAutomation() {
		for (Condition condition : myAutomations.keySet()) {
			EventManager.getEventManager().removeEventCondition(condition);
		}
	}
	

	public void actionPerformed(Object event) {
	}
}

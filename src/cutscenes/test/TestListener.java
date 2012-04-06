package cutscenes.test;

import core.EventListener;

public class TestListener implements EventListener {

	public void actionPerformed(String eventName) {
		System.out.println(eventName);	
	}

}

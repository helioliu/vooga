package cutscenes.test;

import core.EventListener;

public class TestListener implements EventListener {

	public void actionPerformed(Object eventName) {
		
		System.out.println((String) eventName);	
	}

}

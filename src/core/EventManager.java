package core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class EventManager {

	private Map<Condition, String> mapEventConditionToEvent;
	private static EventManager myEventManager;
	private EventQueue myEventQueue;
	private long elapsedTime;

	private EventManager() {
		myEventManager = this;
		myEventQueue = new EventQueue();
		mapEventConditionToEvent = new HashMap<Condition, String>();
	}

	public void addEvent(Event event) {
		myEventQueue.addEvent(event);
	}

	public void addEventCondition(Condition cond, String eventName) {
		mapEventConditionToEvent.put(cond, eventName);
	}

	public void removeEventCondition(Condition condition) {
		mapEventConditionToEvent.remove(condition);
	}

	public void registerEventListener(String eventName, EventListener listener) {
		myEventQueue.registerEventListener(eventName, listener);
	}

	public void unregisterEventListener(String eventName, EventListener listener) {
		myEventQueue.unregisterEventListener(eventName, listener);
	}

	public void sendEvent(String eventName) {
		sendEvent(eventName, null);
	}

	public void sendEvent(final String eventName, final Object obj) {
		final ArrayList<EventListener> listeners = myEventQueue
				.getEventListeners(eventName);

		addEvent(new Event() {
			@Override
			public void run() {
				for (int i = listeners.size() - 1; i >= 0; i--) {
					listeners.get(i).actionPerformed(eventName);
				}
			}

			@Override
			public String toString() {
				return eventName;
			}

		});
	}

	public long getElapsedTime() {
		return elapsedTime;
	}

	public static EventManager getEventManager() {
		if (myEventManager == null) {
			myEventManager = new EventManager();
		}
		return myEventManager;
	}

	public void update(long timeElapsed) {
		elapsedTime = timeElapsed;
		for (Condition cond : mapEventConditionToEvent.keySet()) {
			if (cond.conditionTrue()) {
				sendEvent(mapEventConditionToEvent.get(cond));
			}
		}
		while (myEventQueue.hasEvents()) {
			Event event = myEventQueue.removeEvent();
			event.run();
		}

	}

}
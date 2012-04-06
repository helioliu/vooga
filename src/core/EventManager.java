package core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EventManager {

	private Map<String, ArrayList<EventListener>> mapEventToEventListnerList;
	private Map<EventCondition, String> mapEventConditionToEvent;
	private long elapsedTime;
	private static EventManager myEventManager;

	public EventManager() {
		myEventManager = this;
		mapEventToEventListnerList = new HashMap<String, ArrayList<EventListener>>();
		mapEventConditionToEvent = new HashMap<EventCondition, String>();
	}

	public void addEventCondition(EventCondition cond, String s) {
		mapEventConditionToEvent.put(cond, s);
	}

	public void registerEventListener(String e, EventListener listener) {
		if (!mapEventToEventListnerList.containsKey(e)) {
			ArrayList<EventListener> list = new ArrayList<EventListener>();
			list.add(listener);
			mapEventToEventListnerList.put(e, list);
		} else {
			ArrayList<EventListener> list = mapEventToEventListnerList.get(e);
			list.add(listener);
			mapEventToEventListnerList.put(e, list);
		}
	}

	public void unregisterEventListener(String e) {
		ArrayList<EventListener> list = mapEventToEventListnerList.get(e);
		list.remove(e);
		mapEventToEventListnerList.put(e, list);
	}

	public void sendEvent(String e) {
		for (String event : mapEventToEventListnerList.keySet()) {
			if (event.equals(e)) {
				for (EventListener listener : mapEventToEventListnerList
						.get(event)) {
					listener.actionPerformed(event);
				}
			}
		}
	}

	public static EventManager getEventManager() {
		return myEventManager;
	}

	public long getElapsedTime() {
		return elapsedTime;
	}

	public void update(long timeElapsed) {
		elapsedTime = timeElapsed;
		for (EventCondition cond : mapEventConditionToEvent.keySet()) {
			if (cond.conditionTrue()) {
				sendEvent(mapEventConditionToEvent.get(cond));
			}
		}
	}

}

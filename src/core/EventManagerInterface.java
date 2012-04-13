package core;

public interface EventManagerInterface {

	public void addEvent(String eventName);
	
	public void addEventCondition(EventCondition cond, String s);
	
	public void removeEventCondition(EventCondition condition);
	
	public void registerEventListener(String e, EventListener listener);

	public void unregisterEventListener(String e, EventListener listener);
	
	public void sendEvent(String eventName);
	
}

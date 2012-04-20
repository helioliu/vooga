package core;

public interface EventManagerInterface {

	public void addEvent(Event event);
	
	public void addEventCondition(Condition cond, String s);
	
	public void removeEventCondition(Condition condition);
	
	public void registerEventListener(String e, EventListener listener);

	public void unregisterEventListener(String e, EventListener listener);
	
	public void sendEvent(String eventName);
	
}

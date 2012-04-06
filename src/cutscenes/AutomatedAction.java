package cutscenes;

public class AutomatedAction {
	private String event;
	private Integer duration;
	
	public AutomatedAction(String eventName, Integer howLong) {
		event = eventName;
		duration = howLong;
	}
	
	public String getEventName() {
		return event;
	}
	
	public Integer getDuration() {
		return duration;
	}
}

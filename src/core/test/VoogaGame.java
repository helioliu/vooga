package core.test;

import java.awt.Graphics2D;

import com.golden.gamedev.Game;

import core.*;


public class VoogaGame extends Game implements EventManagerInterface{
	
	private EventManager myEventManager = new EventManager();
	
	@Override
	public void initResources() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics2D arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(long arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
	@Override
	public void addEvent(String eventName) {
		myEventManager.getEventManager().addEvent(eventName);		
	}

	@Override
	public void addEventCondition(EventCondition cond, String s) {
		myEventManager.getEventManager().addEventCondition(EventCondition cond, String s);		
		
	}

	@Override
	public void removeEventCondition(EventCondition condition) {
		myEventManager.getEventManager().removeEventCondition(EventCondition condition);		
	}

	@Override
	public void registerEventListener(String e, EventListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unregisterEventListener(String e, EventListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendEvent(String eventName) {
		// TODO Auto-generated method stub
		
	}



}

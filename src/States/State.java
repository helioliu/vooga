package States;

import java.util.HashMap;
import java.util.Map;

import sprites.GeneralSprite;

import com.golden.gamedev.object.Sprite;

import core.EventListener;
import core.EventManager;

public abstract class State {
	private GeneralSprite mySprite;
	private Map<String, EventListener> myMap;
	private EventManager em;
	private double myGravityValue;
	
	public State(GeneralSprite s)
	{
		myMap = new HashMap<String, EventListener>();
		mySprite = s;
		em = EventManager.getEventManager();
	}
	
	public void activateListener(String s)
	{
		em.registerEventListener(s, myMap.get(s));
	}
	public void activateAllListeners()
	{
		for(String s: myMap.keySet())
		{
			em.registerEventListener(s, myMap.get(s));
		}
		
	}
	public void deactivateListener(String s)
	{
		em.unregisterEventListener(s, myMap.get(s));
	}
	public void deactivateAllListeners()
	{
		for(String s: myMap.keySet())
		{
			em.unregisterEventListener(s, myMap.get(s));
		}
	}
	public void addAction(String eventName, EventListener el)
	{
		myMap.put(eventName, el);
	}
	public int hashCode()
	{
		return this.getClass().getName().hashCode();
	}

	public double getMyGravityValue() {
		return myGravityValue;
	}

	public void setMyGravityValue(double myGravityValue) {
		this.myGravityValue = myGravityValue;
	}
	public GeneralSprite getSprite()
	{
		return mySprite;
	}
	
	
	
	

}

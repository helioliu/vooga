package States;

import java.util.HashMap;
import java.util.Map;

import com.golden.gamedev.object.Sprite;

import core.EventListener;
import core.EventManager;

public abstract class CharacterState implements State{
	protected Sprite mySprite;
	protected Map<String, EventListener> myMap;
	protected EventManager em;
	
	
	
	public CharacterState(Sprite s)
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
		em.unregisterEventListener(s);
	}
	public void deactivateAllListeners()
	{
		for(String s: myMap.keySet())
		{
			em.unregisterEventListener(s);
		}
	}
}

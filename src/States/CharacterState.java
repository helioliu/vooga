package States;

import java.util.HashMap;
import java.util.Map;

import com.golden.gamedev.object.Sprite;

public abstract class CharacterState implements State{
	protected Sprite mySprite;
	protected Map<String, EventListener> myMap;
	
	
	
	public CharacterState(Sprite s)
	{
		myMap = new HashMap<String, EventListener>();
		mySprite = s;
	}
	
	public void activateListener(String s)
	{
		EventManager.register(s, myMap.get(s));
	}
	public void activateAllListeners()
	{
		for(String s: myMap.getKeySet())
		{
			EventManager.register(s, myMap.get(s));
		}
	}
	public void deactivateListener(String s)
	{
		EventManager.unregister(s, myMap.get(s));
	}
	public void deactivateAllListeners()
	{
		for(String s: myMap.getKeySet())
		{
			EventManager.unregister(s, myMap.get(s));
		}
	}
}

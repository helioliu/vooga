package hudDisplay;

import java.util.HashMap;
import java.util.Map;

import com.golden.gamedev.object.Sprite;

import core.EventListener;
import core.EventManager;
import sprites.BryanSprite;
import States.OnLandStateWithScore;

public class HUDEventManager{
	protected Map<String, EventListener> myMap;
	protected EventManager em;
	protected HeadsUpDisplay myHud;

		public HUDEventManager(HeadsUpDisplay hud)
		{		
			myHud = hud;
			myMap = new HashMap<String, EventListener>();
			em = EventManager.getEventManager();
		}
		
		public void activateListener(String s)
		{
			em.registerEventListener(s, myMap.get(s));
		}
		public void activateAllListeners()
		{
			System.out.println("registering listeners");
			for(String s: myMap.keySet())
			{
				System.out.println(s);
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
		
	}



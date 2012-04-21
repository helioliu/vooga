package core;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Map;

import com.golden.gamedev.Game;
import com.golden.gamedev.GameLoader;



public abstract class VoogaGame extends Game implements EventManagerInterface {

	public VoogaManager myVoogaManager;
	public Map<Integer, VoogaManager> myManagers;
	
	private static final int MENU = 0;
	private static final int GAME = 1;

	public static void launchGame(VoogaGame game, Dimension dimension,
			boolean fullScreen) {
		GameLoader loader = new GameLoader();
		loader.setup(game, dimension, fullScreen);
		loader.start();
	}

	@Override
	protected void initEngine() {
		super.initEngine();
		myManagers = new HashMap<Integer, VoogaManager>();		
		myVoogaManager = new VoogaManager() {
			
			private EventManager myEventManager = new EventManager();

			@Override
			public void update(long elapsedTime) {
				return;
			}

			@Override
			public void render(Graphics2D g) {
				return;
			}

			@Override
			public EventManager getEventManager() {
				return myEventManager;
			}
		};
	}
	

    public EventManager getEventManager ()
    {
        return myVoogaManager.getEventManager();
    }

	
	@Override
	public void addEvent(Event event){
		getEventManager().addEvent(event);
	}

	@Override
	public void addEventCondition(EventCondition cond, String s){
		getEventManager().addEventCondition(cond, s);
	}
	
	@Override
	public void removeEventCondition(EventCondition condition){
		getEventManager().removeEventCondition(condition);
	}
	
	@Override
	public void registerEventListener(String e, EventListener listener){
		getEventManager().registerEventListener(e,listener);
	}
	
	@Override
	public void unregisterEventListener(String e, EventListener listener){
		getEventManager().unregisterEventListener(e, listener);
	}

	@Override
	public void sendEvent(String eventName){
		getEventManager().sendEvent(eventName);
	}

	
}

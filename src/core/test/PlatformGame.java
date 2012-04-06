package core.test;

import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Map;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.Timer;

import core.EventManager;

import States.*;


public class PlatformGame extends Game{

	Map<String, State> stateMap;
	EventManager eventManager;
	PlayField playfield;
	
	public void initResources() {
		stateMap = new HashMap<String, State>();
		eventManager = new EventManager();
		playfield = new PlayField();

		TimerEventCondition timerEventCond = new TimerEventCondition(new Timer(1000));
		TimerEventListener timerEventLis = new TimerEventListener();
		eventManager.addEventCondition(timerEventCond, "Print Event");
		eventManager.registerEventListener("Print Event",timerEventLis);
		
		Sprite s1 = new Sprite(100, 100);
		Sprite s2 = new Sprite(500, 100);
		
		playfield.add(s1);
		playfield.add(s2);

		s1.setHorizontalSpeed(0.1);
		s2.setHorizontalSpeed(-0.1);
		
		SpriteCloseEventCondition cond2 = new SpriteCloseEventCondition(s1, s2);
		SpriteCloseEventListener listener2 = new SpriteCloseEventListener();
		eventManager.addEventCondition(cond2, "Sprite Close Event");
		eventManager.registerEventListener("Sprite Close Event",listener2);
		
	}
	
	public void render(Graphics2D arg0) {
	
	}
	
	public void update(long elapsedTime) {
		eventManager.update(elapsedTime);
		playfield.update(elapsedTime);
	}

}

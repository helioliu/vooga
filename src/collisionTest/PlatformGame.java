package collisionTest;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import collisionType.HitboxCollision;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.AdvanceSpriteGroup;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
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

		//TimerEventCondition timerEventCond = new TimerEventCondition(new Timer(1000));
		//TimerEventListener timerEventLis = new TimerEventListener();
		//eventManager.addEventCondition(timerEventCond, "Print Event");
		//eventManager.registerEventListener("Print Event",timerEventLis);
		
		Sprite s1 = new Sprite(getImage("mario1.png"), 0, 100);
		Sprite s2 = new BoxySprite(getImages("mario1.png", 1, 1), 300, 100);
		
		playfield.add(s1);
		playfield.add(s2);

		s1.setHorizontalSpeed(0.1);
		s2.setHorizontalSpeed(-0.1);
		
		SpriteGroup CHAR1  		= playfield.addGroup(new AdvanceSpriteGroup("Char1", 0));
		SpriteGroup CHAR2 		= playfield.addGroup(new AdvanceSpriteGroup("Char2", 300));
		CHAR1.add(s1);
		CHAR2.add(s2);
		
		playfield.addCollisionGroup(CHAR1, CHAR2, new HitboxCollision());
		
		
		//SpriteCloseEventCondition cond2 = new SpriteCloseEventCondition(s1, s2);
		//SpriteCloseEventListener listener2 = new SpriteCloseEventListener();
		//eventManager.addEventCondition(cond2, "Sprite Close Event");
		//eventManager.registerEventListener("Sprite Close Event",listener2);
		
	}
	
	public void render(Graphics2D arg0) {
		playfield.render(arg0);
	}
	
	public void update(long elapsedTime) {
		eventManager.update(elapsedTime);
		playfield.update(elapsedTime);
	}

}

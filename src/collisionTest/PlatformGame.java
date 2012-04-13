package collisionTest;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import collisionType.HitboxHitboxCollision;
import collisionType.HitboxNonhitboxCollision;

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
		eventManager = EventManager.getEventManager();
		playfield = new PlayField();

		Sprite s1 = new BoxySprite(getImages("test.png", 1, 1), 0, 100);
		Sprite s2 = new DysboxySprite(getImages("test.png", 1, 1), 600, 100);
		
		playfield.add(s1);
		playfield.add(s2);

		s1.setHorizontalSpeed(0.5);
		s2.setHorizontalSpeed(-.5);
		
		SpriteGroup CHAR1  		= playfield.addGroup(new AdvanceSpriteGroup("Char1", 0));
		SpriteGroup CHAR2 		= playfield.addGroup(new AdvanceSpriteGroup("Char2", 300));
		CHAR1.add(s1);
		CHAR2.add(s2);
		
		playfield.addCollisionGroup(CHAR1, CHAR2, new HitboxHitboxCollision());
		
	}
	
	public void render(Graphics2D arg0) {
		playfield.render(arg0);
	}
	
	public void update(long elapsedTime) {
		eventManager.update(elapsedTime);
		playfield.update(elapsedTime);
	}

}

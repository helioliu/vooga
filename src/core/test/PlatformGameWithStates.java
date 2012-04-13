package core.test;

import hudDisplay.BarDisplay;
import hudDisplay.HeadsUpDisplay;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import sprites.TestCharacterWithStates;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.CollisionManager;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.Timer;
import com.golden.gamedev.object.background.ColorBackground;
import com.golden.gamedev.object.collision.BasicCollisionGroup;

import core.EventManager;

import States.*;


public class PlatformGameWithStates extends Game{
	TestCharacterWithStates s1;
	Map<String, State> stateMap;
	PlayField playfield;
	CollisionManager collisionTypeWall;
	HeadsUpDisplay HUD;

	
	public void initResources() {
		stateMap = new HashMap<String, State>();
		playfield = new PlayField();
		playfield.setBackground(new ColorBackground(Color.LIGHT_GRAY, 1200, 900));
		
		s1 = new TestCharacterWithStates();
		s1.setImage(getImage("images/mario1.png"));
		s1.setLocation(300, 200);
		SpriteGroup character = new SpriteGroup("character");
		character.add(s1);
		
		HUD = new HeadsUpDisplay(0,0,s1);
		BarDisplay healthbar = new BarDisplay(getImage("images/healthBar.png",false), 500, 0);
		HUD.add(healthbar, "healthbar");
		
		Sprite wall = new Sprite(getImage("images/block.png"));
		wall.setLocation(300,400);
		SpriteGroup walls = new SpriteGroup("walls");
		walls.add(wall);
		
		collisionTypeWall = new WallCollision();
		collisionTypeWall.setCollisionGroup(character, walls);
		
		playfield.addGroup(character);
		playfield.addGroup(walls);
		
		
		
	}
	
	public void render(Graphics2D arg0) {
	playfield.render(arg0);
	collisionTypeWall.checkCollision();
	HUD.render(arg0);
	}
	
	public void update(long elapsedTime) {
		EventManager.getEventManager().update(elapsedTime);
		playfield.update(elapsedTime);
		HUD.update(elapsedTime);

		
		if (keyDown(KeyEvent.VK_LEFT))
		{
			EventManager.getEventManager().sendEvent("left-key");
		}
		if (keyDown(KeyEvent.VK_RIGHT))
		{
			EventManager.getEventManager().sendEvent("right-key");
		}
		if (keyDown(KeyEvent.VK_UP))
		{
			s1.move(0, 1);
			EventManager.getEventManager().sendEvent("up-key");
			
		}
	}
	
	class WallCollision extends BasicCollisionGroup {

	    public WallCollision() {
	    	pixelPerfectCollision = true;
	    }

	    public void collided(Sprite s1, Sprite s2) {
	    	EventManager.getEventManager().sendEvent("floor collide");
	    	EventManager.getEventManager().sendEvent("got hit");
	        
	    }

	}

}

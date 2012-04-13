package core.test;

import hudDisplay.BarItem;
import hudDisplay.HeadsUpDisplay;
import hudDisplay.ScoreItem;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import sprites.TestCharacterWithStates;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.CollisionManager;
import com.golden.gamedev.object.GameFont;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.background.ColorBackground;
import com.golden.gamedev.object.collision.BasicCollisionGroup;

import core.EventManager;

import States.*;


public class PlatformGameWithStates extends Game{
	TestCharacterWithStates s1;
	Map<String, State> stateMap;
	EventManager eventManager;
	PlayField playfield;
	CollisionManager collisionTypeWall;
	
	HeadsUpDisplay HUD;
	GameFont scoreFont;

	
	public void initResources() {
		stateMap = new HashMap<String, State>();
		eventManager = new EventManager();
		playfield = new PlayField();
		playfield.setBackground(new ColorBackground(Color.LIGHT_GRAY, 1200, 900));
		
		s1 = new TestCharacterWithStates();
		s1.setImage(getImage("images/mario1.png"));
		s1.setLocation(300, 200);
		s1.createScore("health", 300);
		s1.createScore("score",0);
		SpriteGroup character = new SpriteGroup("character");
		character.add(s1);
		
		
		
		HUD = new HeadsUpDisplay(getImage("images/EmptyHud.png", false), 0, 0);
		
		BarItem healthbar = new BarItem(getImage("images/healthBar.png", false), 500 ,0, "health", s1);
		HUD.addGraphicItem(healthbar);
		
		scoreFont = fontManager.getFont(getImages("images/Score_Font.png", 8, 12));
		ScoreItem Score = new ScoreItem(scoreFont, 300, 0, "score", s1);
		HUD.addScoreItem(Score);
		
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
		eventManager.update(elapsedTime);
		playfield.update(elapsedTime);
		HUD.update(elapsedTime);

		
		if (keyDown(KeyEvent.VK_LEFT))
		{
			eventManager.sendEvent("left-key");
		}
		if (keyDown(KeyEvent.VK_RIGHT))
		{
			eventManager.sendEvent("right-key");
		}
		if (keyDown(KeyEvent.VK_UP))
		{
			s1.move(0, 1);
			eventManager.sendEvent("up-key");
			
		}
	}
	
	class WallCollision extends BasicCollisionGroup {

	    public WallCollision() {
	    	pixelPerfectCollision = true;
	    }

	    public void collided(Sprite s1, Sprite s2) {
	       eventManager.sendEvent("floor collide");
	       eventManager.sendEvent("got hit");
	        
	    }

	}

}

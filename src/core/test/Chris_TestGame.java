package core.test;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import sprites.Chris_TestSprite;
import States.State;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.CollisionManager;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.Timer;
import com.golden.gamedev.object.background.ColorBackground;
import com.golden.gamedev.object.collision.BasicCollisionGroup;

import core.EventManager;
import cutscenes.Cutscene;
import cutscenes.CutsceneTrigger;


public class Chris_TestGame extends Game{
	Sprite s1;
	Map<String, State> stateMap;
	PlayField playfield;
	CollisionManager collisionTypeWall;
	


	
	public void initResources() {
//		stateMap = new HashMap<String, State>();
		playfield = new PlayField();
		playfield.setBackground(new ColorBackground(Color.LIGHT_GRAY, 1200, 900));
		
		s1 = new Chris_TestSprite();
//		BufferedImage[] images = new BufferedImage[1];
//		images[0] = ;
		s1.setImage(getImage("images/mario1.png"));
		s1.setLocation(300, 200);
		SpriteGroup character = new SpriteGroup("character");
		character.add(s1);
		


		Sprite wall1 = new Sprite(getImage("images/block.png"));
		wall1.setLocation(350,400);
		Sprite wall2 = new Sprite(getImage("images/block.png"));
		wall2.setLocation(300,400);
		Sprite wall3 = new Sprite(getImage("images/block.png"));
		wall3.setLocation(200,400);
		Sprite wall4 = new Sprite(getImage("images/block.png"));
		wall4.setLocation(250,400);


		SpriteGroup walls = new SpriteGroup("walls");
		walls.add(wall1);
		walls.add(wall2);
		walls.add(wall3);
		walls.add(wall4);
		
		
		collisionTypeWall = new WallCollision();
		collisionTypeWall.setCollisionGroup(character, walls);
		
		playfield.addGroup(character);
		playfield.addGroup(walls);
		


		
		
		
	}
	
	public void render(Graphics2D arg0) {
	playfield.render(arg0);
	collisionTypeWall.checkCollision();
//	HUD.render(arg0);
	}
	
	public void update(long elapsedTime) {
		//Cutscene Code
//		if(cutTimer.action(elapsedTime)) {
//			trigger.triggerCutscene();
//			cutTimer.setActive(false);
//		}
//		cutscene.update(elapsedTime);
		
		EventManager.getEventManager().update(elapsedTime);
		playfield.update(elapsedTime);
//		HUD.update(elapsedTime);
		

		
		if (keyDown(KeyEvent.VK_LEFT))
		{
			EventManager.getEventManager().sendEvent("left");
		}
		if (keyDown(KeyEvent.VK_RIGHT))
		{
			EventManager.getEventManager().sendEvent("right");
		}
		if (keyDown(KeyEvent.VK_UP))
		{
			EventManager.getEventManager().sendEvent("up");	
		}
		if (keyDown(KeyEvent.VK_DOWN))
		{
			EventManager.getEventManager().sendEvent("down");	
		}
	}
	
	class WallCollision extends BasicCollisionGroup {

	    public WallCollision() {
	    	pixelPerfectCollision = true;
	    }

	    public void collided(Sprite s1, Sprite s2) {
	    	EventManager.getEventManager().sendEvent("floor collide");
//	    	EventManager.getEventManager().sendEvent("switchstates");
	    	
	    }

	}

}

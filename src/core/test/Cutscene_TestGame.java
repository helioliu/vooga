package core.test;

import input.InputManager;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import sprites.Chris_TestSprite;
import States.State;

import com.golden.gamedev.Game;
import com.golden.gamedev.engine.BaseIO;
import com.golden.gamedev.engine.BaseLoader;
import com.golden.gamedev.engine.input.AWTInput;
import com.golden.gamedev.object.Background;
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

public class Cutscene_TestGame extends Game {
	Sprite s1;
	Map<String, State> stateMap;
	PlayField playfield;
	CollisionManager collisionTypeWall;

	// Cutscene Code
	Cutscene cutscene;
	Timer cutTimer;
	CutsceneTrigger trigger;
	InputManager input;

	// HeadsUpDisplay HUD;

	protected void initEngine() {
		super.initEngine();
		this.bsInput = new AWTInput(this.bsGraphics.getComponent());
	}

	public void initResources() {
		input = new InputManager(this.bsGraphics.getComponent());
		stateMap = new HashMap<String, State>();
		playfield = new PlayField();
		playfield
				.setBackground(new ColorBackground(Color.LIGHT_GRAY, 1200, 900));

		s1 = new Chris_TestSprite();
		s1.setImage(getImage("images/mario1.png"));
		s1.setLocation(300, 200);
		SpriteGroup character = new SpriteGroup("character");
		character.add(s1);

		// HUD = new HeadsUpDisplay(0,0,s1);
		// BarDisplay healthbar = new
		// BarDisplay(getImage("images/healthBar.png",false), 500, 0);
		// HUD.add(healthbar, "healthbar");

		CutsceneTrigger wall = new CutsceneTrigger(getImage("images/block.png"));

		wall.setLocation(300, 400);
		SpriteGroup walls = new SpriteGroup("walls");
		walls.add(wall);

		collisionTypeWall = new CutsceneCollision();
		collisionTypeWall.setCollisionGroup(character, walls);

		playfield.addGroup(character);
		playfield.addGroup(walls);

		// Cutscene Code
		cutscene = new Cutscene("src/cutscenes/test/testCutsceneScript.script",
				14000);
		trigger = wall;
		cutTimer = new Timer(10);

	}

	public void render(Graphics2D arg0) {
		playfield.render(arg0);
		collisionTypeWall.checkCollision();
		// HUD.render(arg0);
	}

	public void update(long elapsedTime) {
		// Cutscene Code
		// if(cutTimer.action(elapsedTime)) {
		// trigger.triggerCutscene();
		// cutTimer.setActive(false);
		// }
		cutscene.update(elapsedTime);
		EventManager.getEventManager().update(elapsedTime);
		playfield.update(elapsedTime);
		// HUD.update(elapsedTime);

		if (keyDown(KeyEvent.VK_LEFT)) {
			EventManager.getEventManager().sendEvent("left");
		}
		if (keyDown(KeyEvent.VK_RIGHT)) {
			EventManager.getEventManager().sendEvent("right");
		}
		if (keyDown(KeyEvent.VK_UP)) {
			EventManager.getEventManager().sendEvent("up");
		}
		if (keyDown(KeyEvent.VK_DOWN)) {
			EventManager.getEventManager().sendEvent("down");
		}
	}

	class CutsceneCollision extends BasicCollisionGroup {

		public CutsceneCollision() {
			pixelPerfectCollision = true;
		}

		public void collided(Sprite s1, Sprite s2) {
			EventManager.getEventManager().sendEvent("cutscene-begin");
		}

	}

}

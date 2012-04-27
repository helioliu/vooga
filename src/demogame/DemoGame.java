package demogame;

import hudDisplay.HeadsUpDisplay;
import hudDisplay.NumberStat;
import hudDisplay.TextItem;
import input.InputManager;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import sprites.Flag;
import sprites.GeneralSprite;
import sprites.HomingEnemy;
import collisionType.AbstractHitboxNonhitboxCollision;
import collisions.Hitbox;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.GameFont;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.background.ColorBackground;
import com.golden.gamedev.object.collision.BasicCollisionGroup;

import core.Condition;
import core.EventManager;
import core.conditions.EventTriggeredCondition;
import core.conditions.GetCloseCondition;
import cutscenes.Cutscene;
import cutscenes.CutsceneAutomation;
import cutscenes.EventAutomation;
import demogame.sprites.MainCharacter;

public class DemoGame extends Game {
	private String levelFileName;
	private PlayField myPlayField;
	private GeneralSprite mainChar;
	private Cutscene levelOver;
	private static final double gravity = .002;
	private NumberStat timer;
	private HeadsUpDisplay HUD;

	public DemoGame (String levelFileName) {
		this.levelFileName = levelFileName;
	}
	
    protected void initEngine() {
		super.initEngine();
		this.bsInput = new InputManager(this.bsGraphics.getComponent());
	}
	
	public void initResources() {
		setMaskColor(Color.WHITE);
		myPlayField = new PlayField();
		myPlayField = new LevelBuilder(myPlayField, levelFileName).createLevel();
		

		
		GeneralSprite mushroom = new GeneralSprite(getImage("images/mushroom.png"), 500, 300);
		SpriteGroup mushrooms = new SpriteGroup("Mushrooms");
		mushrooms.add(mushroom);
		myPlayField.addGroup(mushrooms);
		
		GeneralSprite jetpack = new GeneralSprite(getImage("images/rocket.png"), 1200, 300);
		SpriteGroup jetpacks = new SpriteGroup("Jetpacks");
		jetpacks.add(jetpack);
		myPlayField.addGroup(jetpacks);
		//adding homing enemies

        
        SpriteGroup home = myPlayField.getGroup("sprites.HomingEnemy");
        for (Sprite enemy : home.getSprites()) {
        	if (enemy== null)
        		break;
        	Condition near = new GetCloseCondition(enemy,mainChar,500,true);
            Condition far = new GetCloseCondition(enemy,mainChar,500,false);
            EventManager.getEventManager().addEventCondition(near, "homing"+enemy.hashCode());
            EventManager.getEventManager().addEventCondition(far, "stationary"+enemy.hashCode());
        }
		
		
		myPlayField.addCollisionGroup(myPlayField.getGroup("sprites.Character"), myPlayField.getGroup(sprites.), new PlatformCollision());
		myPlayField.addCollisionGroup(myPlayField.getGroup("sprites.Character"),myPlayField.getGroup("sprites.Flag"),new FlagCollision());
		myPlayField.addCollisionGroup(myPlayField.getGroup("sprites.Character"),myPlayField.getGroup(sprites.),new MushroomCollision());
		myPlayField.addCollisionGroup(myPlayField.getGroup("sprites.Character"),jetpacks,new JetPackCollision());
		
		//make the end-of-level cutscene
		EventAutomation aOne = new CutsceneAutomation();
		aOne.addTimedAutomation(5, 10000, "slide-down-pole");
		EventAutomation aTwo = new CutsceneAutomation("src/demogame/jump_off.script");
		aOne.addTransition(new EventTriggeredCondition("floor collide"), aTwo);
		levelOver = new Cutscene(aOne, "flag-hit", "end-level");
	}
	


	public void render(Graphics2D g) {
		myPlayField.render(g);
		HUD.render(g);
	}

	public void update(long timeElapsed) {
		EventManager.getEventManager().update(timeElapsed);
		myPlayField.getBackground().setToCenter(mainChar);
		myPlayField.update(timeElapsed);
		levelOver.update(timeElapsed);
		HUD.update(timeElapsed);
		timer.update(timeElapsed);
		
//		SpriteGroup homing = myPlayField.getGroup("homing enemies");
//		for (Sprite enemy : homing.getSprites()) {
//			if (enemy==null)
//				break;
////			System.out.println(enemy);
//			if(enemy.getDistance(mainChar)>100){
//	            EventManager.getEventManager().sendEvent("stationary"+enemy.hashCode());
//	        }
//	        else{
//	            EventManager.getEventManager().sendEvent("homing"+enemy.hashCode());
//	        }
//		}
		
	}

	class PlatformCollision extends BasicCollisionGroup {

        public PlatformCollision() {
            pixelPerfectCollision = true;
        }

        public void collided(Sprite s1, Sprite s2) {
            EventManager.getEventManager().sendEvent("floor collide");
        }
	}
	class MushroomCollision extends BasicCollisionGroup{
		public MushroomCollision() {
            pixelPerfectCollision = true;
        }

        public void collided(Sprite s1, Sprite s2) {
            EventManager.getEventManager().sendEvent("mushroom");
            s2.setActive(false);
        }
	}
	class JetPackCollision extends BasicCollisionGroup{
		public JetPackCollision() {
            pixelPerfectCollision = true;
        }

        public void collided(Sprite s1, Sprite s2) {
            EventManager.getEventManager().sendEvent("pwrup");
            s2.setActive(false);
        }
	}
	
	class FlagCollision extends AbstractHitboxNonhitboxCollision {
		boolean hitYet;
		
		public FlagCollision () {
			hitYet = false;
			pixelPerfectCollision = true;
		}

		@Override
		protected void spriteCollided(Sprite s1, Sprite s2) {
		}

		@Override
		protected void hitboxSpriteCollided(Sprite s1, Hitbox h1, Sprite s2) {
			flagHit();
			if(!hitYet) {
				EventManager.getEventManager().sendEvent("flag-hit");	
				hitYet = true;
			}
		}
		
	}
	
	private void flagHit() {
		SpriteGroup enemies = myPlayField.getGroup("homing enemies");
		enemies.clear();
		
	}
}
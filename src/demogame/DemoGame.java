package demogame;

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
//		myPlayField = new LevelBuilder(myPlayField, levelFileName).createLevel();
		Background b = new ColorBackground(Color.LIGHT_GRAY, 2000, 480);
		myPlayField.setBackground(b);
		
		SpriteGroup platforms = makePlatforms();
		myPlayField.addGroup(platforms);
		GeneralSprite flag = new Flag(getImage("images/finalflag.png"), 1750, 106);
		SpriteGroup fg = new SpriteGroup("flag");
		fg.add(flag);
		myPlayField.addGroup(fg);
		
		GeneralSprite castle = new GeneralSprite(getImage("images/castle.gif"), 1800, 300);
		myPlayField.add(castle);
		
		mainChar = new MainCharacter();
		mainChar.setImages(getImages("images/mariocharpng.png",3,2));
		mainChar.setLocation(200, 100);
		mainChar.setAnimate(false);
		SpriteGroup chargroup = new SpriteGroup("Character");
		chargroup.add(mainChar);
		myPlayField.addGroup(chargroup);
		
		//adding homing enemies
		Condition near;
		Condition far;
		GeneralSprite enemy1 = new HomingEnemy(mainChar);
        enemy1.setImage(getImage("images/boo.jpg"));
        enemy1.setLocation(300, 300);
        near = new GetCloseCondition(enemy1,mainChar,600,true);
        far = new GetCloseCondition(enemy1,mainChar,600,false);        
        EventManager.getEventManager().addEventCondition(near, "homing"+enemy1.hashCode());
        EventManager.getEventManager().addEventCondition(far, "stationary"+enemy1.hashCode());
        
        GeneralSprite enemy2 = new HomingEnemy(mainChar);
        enemy2.setImage(getImage("images/boo.jpg"));
        enemy2.setLocation(700, 300);
        near = new GetCloseCondition(enemy2,mainChar,600,true);
        far = new GetCloseCondition(enemy2,mainChar,600,false); 
        EventManager.getEventManager().addEventCondition(near, "homing"+enemy2.hashCode());
        EventManager.getEventManager().addEventCondition(far, "stationary"+enemy2.hashCode());
        
        GeneralSprite enemy3 = new HomingEnemy(mainChar);
        enemy3.setImage(getImage("images/boo.jpg"));
        enemy3.setLocation(1200, 300);
        near = new GetCloseCondition(enemy3,mainChar,600,true);
        far = new GetCloseCondition(enemy3,mainChar,600,false); 
        EventManager.getEventManager().addEventCondition(near, "homing"+enemy3.hashCode());
        EventManager.getEventManager().addEventCondition(far, "stationary"+enemy3.hashCode());
        
        GeneralSprite enemy4 = new HomingEnemy(mainChar);
        enemy4.setImage(getImage("images/boo.jpg"));
        enemy4.setLocation(1700, 300); 
        near = new GetCloseCondition(enemy4,mainChar,600,true);
        far = new GetCloseCondition(enemy4,mainChar,600,false); 
        EventManager.getEventManager().addEventCondition(near, "homing"+enemy4.hashCode());
        EventManager.getEventManager().addEventCondition(far, "stationary"+enemy4.hashCode());
        
        SpriteGroup homing = new SpriteGroup("homing enemies");
        homing.add(enemy1);
        homing.add(enemy2);
        homing.add(enemy3);
        homing.add(enemy4);
        myPlayField.addGroup(homing);
		
		
		myPlayField.addCollisionGroup(chargroup, platforms, new PlatformCollision());
		myPlayField.addCollisionGroup(chargroup,fg,new FlagCollision());
		
		//make the end-of-level cutscene
		EventAutomation aOne = new CutsceneAutomation();
		aOne.addTimedAutomation(5, 10000, "slide-down-pole");
		EventAutomation aTwo = new CutsceneAutomation("src/demogame/jump_off.script");
		aOne.addTransition(new EventTriggeredCondition("floor collide"), aTwo);
		levelOver = new Cutscene(aOne, "flag-hit", "end-level");
	}
	
	private SpriteGroup makePlatforms() {
		SpriteGroup s = new SpriteGroup("Platforms");
		BufferedImage image = getImage("images/bricks1.png");
		for(int i=0; i < 1990; i+=32) {
			if(i<300 | i>400)
				s.add(new GeneralSprite(image,i,400));
		}
		return s;
	}

	public void render(Graphics2D g) {
		myPlayField.render(g);
	}

	public void update(long timeElapsed) {
		EventManager.getEventManager().update(timeElapsed);
		myPlayField.getBackground().setToCenter(mainChar);
		myPlayField.update(timeElapsed);
		levelOver.update(timeElapsed);
		
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
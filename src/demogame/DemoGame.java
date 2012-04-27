package demogame;

import hudDisplay.HeadsUpDisplay;
import hudDisplay.NumberStat;
import hudDisplay.TextItem;
import input.InputManager;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import levelBuilder.PlayFieldBuilder;

import sprites.Flag;
import sprites.GeneralSprite;
import sprites.HomingEnemy;
import sprites.Jetpack;
import sprites.LifeMushroom;
import sprites.Platform;
import sprites.MainCharacter;
import SpriteAction.JetPack;
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
import core.EventListener;
import core.EventManager;
import core.conditions.EventTriggeredCondition;
import core.conditions.GetCloseCondition;
import cutscenes.Cutscene;
import cutscenes.CutsceneAutomation;
import cutscenes.EventAutomation;

public class DemoGame extends Game implements EventListener {
	private String levelFileName;
	private PlayField myPlayField;

	private Cutscene levelOver;
	Cutscene death;
	private MainCharacter mainChar;
	private static final double gravity = .002;
	private String[] levels = {
			"level1.xml",
			"boss.xml"
	};
	private int currentLevel;
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
		EventManager.getEventManager().registerEventListener("end-game", this);
		currentLevel = 1;
		setMaskColor(Color.WHITE);
		myPlayField = new PlayField();
		myPlayField = new PlayFieldBuilder(myPlayField, levelFileName).parseXML();

		SpriteGroup home = myPlayField.getGroup("sprites.HomingEnemy");
        mainChar= (MainCharacter) myPlayField.getGroup("sprites.MainCharacter").getSprites()[0];
             for (Sprite enemy : home.getSprites()) {
        	if (enemy== null)
        		break;
        	HomingEnemy he= (HomingEnemy) enemy;
        	he.setMyTarget(mainChar);
        	Condition near = new GetCloseCondition(he,mainChar,500,true);
            Condition far = new GetCloseCondition(he,mainChar,500,false);
            EventManager.getEventManager().addEventCondition(near, "homing"+enemy.hashCode());
            EventManager.getEventManager().addEventCondition(far, "stationary"+enemy.hashCode());
        }
             

 		HUD = new HeadsUpDisplay(0, 0);

 		GameFont BigFont = fontManager.getFont(getImages("images/Font.png", 8,12));
 		GameFont SmallFont = fontManager.getFont(getImages("images/SmallFont.png", 8,12));

 		TextItem lives = new TextItem(BigFont, 10, 10,mainChar.getStat("lives"),"Mario x ");
 		HUD.addItem(lives);

 		timer = new NumberStat(0);
 		timer.incrementWithTimer(500, 1);
 		TextItem timerScore = new TextItem(BigFont, 300, 10, timer,"Time: ");
 		HUD.addItem(timerScore);

 		TextItem CoinMult = new TextItem(SmallFont, 500, 10, mainChar.getStat("coinMult"),"Coins x ");
 		HUD.addItem(CoinMult);

 		TextItem score = new TextItem(SmallFont, 500, 20,mainChar.getStat("score"));
 		HUD.addItem(score);


        
        SpriteGroup Projectiles = new SpriteGroup("sprites.Projectile");
        myPlayField.addGroup(Projectiles);
		myPlayField.addCollisionGroup(myPlayField.getGroup("sprites.MainCharacter"), myPlayField.getGroup("sprites.Platform"), new PlatformCollision());
		myPlayField.addCollisionGroup(myPlayField.getGroup("sprites.MainCharacter"),myPlayField.getGroup("sprites.Flag"),new FlagCollision());
		myPlayField.addCollisionGroup(myPlayField.getGroup("sprites.MainCharacter"),myPlayField.getGroup("sprites.LifeMushroom"),new MushroomCollision());
		myPlayField.addCollisionGroup(myPlayField.getGroup("sprites.MainCharacter"), myPlayField.getGroup("sprites.Jetpack"),new JetPackCollision());
		myPlayField.addCollisionGroup(myPlayField.getGroup("sprites.MainCharacter"),myPlayField.getGroup("sprites.HomingEnemy"), new EnemyHitCollision());
//    	myPlayField.addCollisionGroup(myPlayField.getGroup("sprites.Projectile"),myPlayField.getGroup("sprites.HomingEnemy"), new EnemyHitCollision());
		

		//make the end-of-level cutscene
		EventAutomation aOne = new CutsceneAutomation();
		aOne.addTimedAutomation(5, 10000, "slide-down-pole");
		EventAutomation aTwo = new CutsceneAutomation("src/demogame/jump_off.script");
		aOne.addTransition(new EventTriggeredCondition("floor collide"), aTwo);
		levelOver = new Cutscene(aOne, "flag-hit", "end-level");
		
		
		EventAutomation a = new CutsceneAutomation("src/demogame/death.script");
		death = new Cutscene(a, "enemy hit", "end-game");
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
		death.update(timeElapsed);
		HUD.update(timeElapsed);
		timer.update(timeElapsed);
		if(click())
		{
			mainChar.Shoot(myPlayField.getGroup("sprites.Projectile"), getMouseX(), getMouseY());

		}
		
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
	
	class EnemyHitCollision extends BasicCollisionGroup {
		public EnemyHitCollision() {
			pixelPerfectCollision = true;
		}

		public void collided(Sprite arg0, Sprite arg1) {
			EventManager.getEventManager().sendEvent("enemy hit");
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

		
		protected void hitboxSpriteCollided(Sprite s1, Hitbox h1, Sprite s2) {
			flagHit();
			if(!hitYet) {
				EventManager.getEventManager().sendEvent("flag-hit");	
				hitYet = true;
			}
		}
		
	}
	
	private void flagHit() {
		SpriteGroup enemies = myPlayField.getGroup("sprites.HomingEnemy");
		enemies.clear();
		
	}

	public void actionPerformed(String object) {
		System.out.println("do whatever we need to to end the game");
		initResources();
	}
	
	
}
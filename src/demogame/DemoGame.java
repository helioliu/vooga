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
import sprites.Jetpack;
import sprites.LifeMushroom;
import sprites.Platform;
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
import demogame.sprites.MainCharacter;

public class DemoGame extends Game implements EventListener {
	private String levelFileName;
	private PlayField myPlayField;
	private MainCharacter mainChar;
	private Cutscene levelOver;
	Cutscene death;
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
		
		mainChar.createStat("lives", new NumberStat(5));
		mainChar.createStat("score", new NumberStat(0));
		mainChar.createStat("coinMult", new NumberStat(0));
		
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
		
		
		
		
		mainChar.setImages(getImages("images/mariocharpng.png",3,2));
		mainChar.setLocation(200, 100);
		mainChar.setAnimate(false);
		SpriteGroup chargroup = new SpriteGroup("Character");
		chargroup.add(mainChar);
		myPlayField.addGroup(chargroup);
		
		

		GeneralSprite mushroom = new LifeMushroom(getImage("images/mushroom.png"), 500, 300);
		SpriteGroup mushrooms = new SpriteGroup("Mushrooms");
		mushrooms.add(mushroom);
		myPlayField.addGroup(mushrooms);
		
		GeneralSprite jetpack = new Jetpack(getImage("images/rocket.png"), 1200, 300);
		SpriteGroup jetpacks = new SpriteGroup("Jetpacks");
		jetpacks.add(jetpack);
		myPlayField.addGroup(jetpacks);
		//adding homing enemies
		HomingEnemy enemy1 = new HomingEnemy();
		enemy1.setMyTarget(mainChar);
        enemy1.setImage(getImage("images/boo.png"));
        enemy1.setLocation(300, 300);
        
        
        GeneralSprite enemy2 = new HomingEnemy();
		enemy1.setMyTarget(mainChar);
        enemy2.setImage(getImage("images/boo.png"));
        enemy2.setLocation(700, 300);
        
        GeneralSprite enemy3 = new HomingEnemy();
		enemy1.setMyTarget(mainChar);
        enemy3.setImage(getImage("images/boo.png"));
        enemy3.setLocation(1200, 300);
        
        GeneralSprite enemy4 = new HomingEnemy();
		enemy1.setMyTarget(mainChar);
        enemy4.setImage(getImage("images/boo.png"));
        enemy4.setLocation(1700, 300); 
        
        SpriteGroup homing = new SpriteGroup("homing enemies");
        homing.add(enemy1);
        homing.add(enemy2);
        homing.add(enemy3);
        homing.add(enemy4);
        myPlayField.addGroup(homing);
        
        SpriteGroup home = myPlayField.getGroup("homing enemies");
        for (Sprite enemy : home.getSprites()) {
        	if (enemy== null)
        		break;
        	Condition near = new GetCloseCondition(enemy,mainChar,500,true);
            Condition far = new GetCloseCondition(enemy,mainChar,500,false);
            EventManager.getEventManager().addEventCondition(near, "homing"+enemy.hashCode());
            EventManager.getEventManager().addEventCondition(far, "stationary"+enemy.hashCode());
        }
        
        SpriteGroup Projectiles = new SpriteGroup("Projectile");
        
		
		myPlayField.addCollisionGroup(chargroup, platforms, new PlatformCollision());
		myPlayField.addCollisionGroup(chargroup,fg,new FlagCollision());
		myPlayField.addCollisionGroup(chargroup,mushrooms,new MushroomCollision());
		myPlayField.addCollisionGroup(chargroup,jetpacks,new JetPackCollision());
		myPlayField.addCollisionGroup(chargroup,home, new EnemyHitCollision());
//		myPlayField.addCollisionGroup(Projectiles,home, new EnemyHitCollision());
		
		//make the end-of-level cutscene
		EventAutomation aOne = new CutsceneAutomation();
		aOne.addTimedAutomation(5, 10000, "slide-down-pole");
		EventAutomation aTwo = new CutsceneAutomation("src/demogame/jump_off.script");
		aOne.addTransition(new EventTriggeredCondition("floor collide"), aTwo);
		levelOver = new Cutscene(aOne, "flag-hit", "end-level");
		
		
		EventAutomation a = new CutsceneAutomation("src/demogame/death.script");
		death = new Cutscene(a, "enemy hit", "end-game");
	}
	
	private SpriteGroup makePlatforms() {
		SpriteGroup s = new SpriteGroup("Platforms");
		BufferedImage image = getImage("images/bricks1.png");
		for(int i=0; i < 1990; i+=32) {
			if(i<300 | i>400)
				s.add(new Platform(image,i,400));
		}
		return s;
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
			mainChar.shoot(myPlayField.getGroup("Projectile"), getMouseX(), getMouseY());
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

	public void actionPerformed(String object) {
		System.out.println("do whatever we need to to end the game");
		initResources();
	}
}
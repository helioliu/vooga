package demogame;

import input.InputManager;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import sprites.Chris_TestSprite;
import sprites.GeneralSprite;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.background.ColorBackground;
import com.golden.gamedev.object.collision.BasicCollisionGroup;

import core.EventListener;
import core.EventManager;
import demogame.sprites.MainCharacter;

public class DemoGame extends Game {
	private String levelFileName;
	private PlayField myPlayField;
	private GeneralSprite mainChar;
	private static final double gravity = .002;

	public DemoGame (String levelFileName) {
		this.levelFileName = levelFileName;
	}
	
    protected void initEngine() {
		super.initEngine();
		this.bsInput = new InputManager(this.bsGraphics.getComponent());
	}
	
	public void initResources() {
		myPlayField = new PlayField();
//		myPlayField = new LevelBuilder(myPlayField, levelFileName).createLevel();
		Background b = new ColorBackground(Color.LIGHT_GRAY, 2000, 480);
		myPlayField.setBackground(b);
		
		SpriteGroup platforms = makePlatforms();
		myPlayField.addGroup(platforms);
		
		mainChar = new MainCharacter();
		mainChar.setImages(getImages("images/mariocharpng.png",3,2));
		mainChar.setLocation(200, 100);
		mainChar.setAnimate(false);
		SpriteGroup chargroup = new SpriteGroup("Character");
		chargroup.add(mainChar);
		myPlayField.addGroup(chargroup);
		
		myPlayField.addCollisionGroup(chargroup, platforms, new PlatformCollision());
//		EventManager.getEventManager().registerEventListener("test", new Test());
//		EventManager.getEventManager().sendEvent("test");
		
	}
	
	private SpriteGroup makePlatforms() {
		SpriteGroup s = new SpriteGroup("Platforms");
		BufferedImage image = getImage("images/bricks1.png");
		for(int i=0; i < 1980; i+=32) {
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
		
	}

	class PlatformCollision extends BasicCollisionGroup {

        public PlatformCollision() {
            pixelPerfectCollision = true;
        }

        public void collided(Sprite s1, Sprite s2) {
            EventManager.getEventManager().sendEvent("floor collide");
        }
	}
	
	class Test implements EventListener {
		
		public void actionPerformed(Object object) {
			System.out.println((String) object);
		}
		
	}
}
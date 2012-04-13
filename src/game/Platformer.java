package game;
import interactiveSprites.InteractiveSprite;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;

import levelBuilder.LevelBuilder;
import levelEditor.*;


import sprites.*;
import sprites.Character;

import com.golden.gamedev.GameObject;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.background.ImageBackground;
import com.golden.gamedev.object.collision.AdvanceCollisionGroup;
import com.golden.gamedev.object.collision.BasicCollisionGroup;
import com.golden.gamedev.object.sprite.VolatileSprite;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Platformer extends GameObject {

	public PlayField playfield;
<<<<<<< HEAD
=======
	public Background background;
	public SpriteGroup CHARACTER, PROJECTILE, POWER_UP, PLATFORM, SPAWNPOINT,
			COINS, BAD_GUYS, INTERACTIVE_SPRITES, EXIT;
>>>>>>> 2e011adff14b67b89fb3e5f3260cda0d502e4d2c

	private PlatformGame game;

	public SpriteGroup CHARACTER, PROJECTILE, POWER_UP, PLATFORM, SPAWNPOINT,
	COINS, BAD_GUYS, SPRINGS, EXIT;
	
	public Platformer(PlatformGame parent) {
		super(parent);
		// TODO Auto-generated constructor stub
		game = parent;
	}




<<<<<<< HEAD
	@Override
	public void initResources() {
		playfield = new LevelBuilder(this).createLevel(PlatformGame.LEVEL_FILES
				.get(PlatformGame.currentLevel));
=======
		File backgroundPathFile= null; 
		BufferedImage myBackground=null;		
				try {
		backgroundPathFile= new File(myGameInfo.getBackground());

			myBackground = ImageIO.read(backgroundPathFile);
		} catch (IOException e1) {
			System.out.print("no background");
		}
		background = new ImageBackground(myBackground);
		playfield = new PlayField(background);
		// create groups
		CHARACTER = playfield.addGroup(new SpriteGroup("Character"));
		PROJECTILE = playfield.addGroup(new SpriteGroup("Projectile"));
		POWER_UP = playfield.addGroup(new SpriteGroup("Power_Up"));
		PLATFORM = playfield.addGroup(new SpriteGroup("Platform"));
		SPAWNPOINT = playfield.addGroup(new SpriteGroup("SPAWNPOINT"));
		COINS = playfield.addGroup(new SpriteGroup("COINS"));
		BAD_GUYS = playfield.addGroup(new SpriteGroup("BAD_GUYS"));
		INTERACTIVE_SPRITES = playfield.addGroup(new SpriteGroup("SPRINGS"));

		for (int i = 0; i < myGameInfo.getList().size(); i++) {
			SpriteInfo info = myGameInfo.getList().get(i);
			String className = info.getClassName();
		    LevelEditable s;
		    try {  s= (LevelEditable) Class.forName(className).newInstance();
		     s.parse(info.getList(), this);
		    } catch (Exception e) {

		    }

		}

		// set up collision groups
		playfield.addCollisionGroup(CHARACTER, PROJECTILE,
				new CharacterProjectileCollision());
		playfield.addCollisionGroup(PROJECTILE, BAD_GUYS,
				new EnemyProjectileCollision());
		playfield.addCollisionGroup(CHARACTER, PLATFORM,
				new SpritePlatformCollision());
		playfield.addCollisionGroup(CHARACTER, INTERACTIVE_SPRITES,
				new CharacterInteractiveSpriteCollision());
		// playfield.addCollisionGroup(CHARACTER, BAD_GUYS,
		// new CharacterEnemyCollision());
		// playfield.addCollisionGroup(CHARACTER, COINS, new CoinCollision());
		// playfield.addCollisionGroup(CHARACTER, POWER_UP,
		// new PowerUpCollision());
		// playfield.addCollisionGroup(CHARACTER, PLATFORM,
		// new PlatformCollision());
		// playfield.addCollisionGroup(CHARACTER, EXIT, new ExitCollision());
		// playfield.addCollisionGroup(BAD_GUYS, PLATFORM,
		// new PlatformCollision());
>>>>>>> 2e011adff14b67b89fb3e5f3260cda0d502e4d2c

	}

	@Override
	public void update(long arg0) {
		playfield.update(arg0);

	}

	@Override
	public void render(Graphics2D arg0) {
		playfield.render(arg0);

	}

	class PowerUpCollision extends BasicCollisionGroup {

		@Override
		public void collided(Sprite s1, Sprite s2) {
			// remove missile, and enemy sprite
			// String type = s2.getPowerUpType();
			// if (type = speed) {
			// s1.getHorizontalSpeed() = s2.getPowerUpValue();
			// }
			// more types here
			s2.setActive(false);

		}
	}

	class EnemyProjectileCollision extends BasicCollisionGroup {

		@Override
		public void collided(Sprite s1, Sprite s2) {

			s1.setActive(false);
			// remove missile, and enemy sprite
			// Config.score = s2.getValue();
			s2.setActive(false);

		}
	}

	class CharacterProjectileCollision extends BasicCollisionGroup {

		@Override
		public void collided(Sprite s1, Sprite s2) {

			// remove missile, and enemy sprite
			// Config.lives -= s2.getDamage();
			s1.setX(0);
			s1.setY(0);
			//
			// s2.setActive(false);
			//
			//
			// initResources();

		}
	}

	class CoinCollision extends BasicCollisionGroup {

		@Override
		public void collided(Sprite s1, Sprite s2) {

			// remove missile, and enemy sprite
			// lives += s2.getValue();
			s2.setActive(false);

		}
	}

	class PlatformCollision extends BasicCollisionGroup {

		@Override
		public void collided(Sprite s1, Sprite s2) {
			// remove missile, and enemy sprite
			s1.setVerticalSpeed(0);

			BufferedImage[] explosion = getImages("explosion.png", 6, 1);
			playfield.add(new VolatileSprite(explosion, s2.getX(), s2.getY()));
		}
	}

	// -> our fighter collide with enemy OR enemy's missile
	class ExitCollision extends BasicCollisionGroup {

		@Override
		public void collided(Sprite s1, Sprite s2) {
			// remove both sprite
			s1.setActive(false);
			s2.setActive(false);

			PlatformGame.LEVEL_FILES = null;
			initResources();

		}
	}

	class CharacterEnemyCollision extends BasicCollisionGroup {

		@Override
		public void collided(Sprite s1, Sprite s2) {
			// remove both sprite
			s1.setActive(false);
			s2.setActive(false);

			PlatformGame.LEVEL_FILES = null;
			initResources();

		}
	}

	class SpritePlatformCollision extends AdvanceCollisionGroup {

		public SpritePlatformCollision() {
			super();
		}

//		public CollisionShape getCollisionShape1(Sprite s1) {
//			rect1.setBounds(s1.getX() + 6, s1.getY() + 12, s1.getWidth() - 12,
//					s1.getHeight() - 18);
//			return rect1;
//		}
//
//		public CollisionShape getCollisionShape2(Sprite s2) {
//			rect2.setBounds(s2.getX() + 6, s2.getY() - 4, s2.getWidth() - 12,
//					s2.getHeight() - 32);
//			return rect2;
//		}

		@Override
		public void collided(Sprite s1, Sprite s2) {
			revertPosition1();
			if (collisionSide == BOTTOM_TOP_COLLISION) {
				s1.setVerticalSpeed(0);
				((Character) s1).jumping = false; 
			}
			if (collisionSide == LEFT_RIGHT_COLLISION
					|| collisionSide == RIGHT_LEFT_COLLISION) {
				s1.setHorizontalSpeed(0);
			}
		}
	}
	
	class CharacterInteractiveSpriteCollision extends AdvanceCollisionGroup {

		@Override
		public void collided(Sprite s1, Sprite s2) {
			
			((InteractiveSprite) s2).primaryAction(this);
			
		}
	}

}

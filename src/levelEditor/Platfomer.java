package levelEditor;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Scanner;

import sprites.Bad_Guys;
import sprites.Character;
import sprites.Platform;

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

public class Platfomer extends GameObject {

	public PlayField playfield;
	public Background background;
	public SpriteGroup CHARACTER, PROJECTILE, POWER_UP, PLATFORM, SPAWNPOINT,
			COINS, BAD_GUYS, EXIT;

	private PlatformGame game;

	public Platfomer(PlatformGame parent) {
		super(parent);
		// TODO Auto-generated constructor stub
		game = parent;
	}

	// configuration file
	public ArrayList<SpriteInfo> LevelInfo;

	@Override
	public void initResources() {

		Gson gson = new Gson();
		Scanner scanner;
		System.out.println(PlatformGame.LEVEL_FILES.get(0));
		try {
			scanner = new Scanner(new File(PlatformGame.LEVEL_FILES
					.get(PlatformGame.currentLevel)));

			String wholeFile = scanner.useDelimiter("\\A").next();
			Type collectionType = new TypeToken<ArrayList<SpriteInfo>>() {
			}.getType();
			LevelInfo = gson.fromJson(wholeFile, collectionType);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(LevelInfo.toString());

		// background = (Background) LevelInfo.get(0);
		background = new ImageBackground(getImage("background.jpg"));
		playfield = new PlayField(background);
		// create groups
		CHARACTER = playfield.addGroup(new SpriteGroup("Character"));
		PROJECTILE = playfield.addGroup(new SpriteGroup("Projectile"));
		POWER_UP = playfield.addGroup(new SpriteGroup("Power_Up"));
		PLATFORM = playfield.addGroup(new SpriteGroup("Platform"));
		SPAWNPOINT = playfield.addGroup(new SpriteGroup("SPAWNPOINT"));
		COINS = playfield.addGroup(new SpriteGroup("COINS"));
		BAD_GUYS = playfield.addGroup(new SpriteGroup("BAD_GUYS"));

		for (int i = 0; i < LevelInfo.size(); i++) {
			SpriteInfo info = LevelInfo.get(i);
			String type = info.getType();
			if (type.equals("Character")) {
				Character myCharacter;

				myCharacter = new Character(this, getImage(info.getPath()),
						info);
				CHARACTER.add(myCharacter);

			}
			if (type.equals("Bad_Guys")) {
				Bad_Guys myBad_Guys;
				myBad_Guys = new Bad_Guys(this, getImage(info.getPath()), info);

				BAD_GUYS.add(myBad_Guys);

			}
			if (type.equals("Platform")) {
				Platform myPlatform;
				myPlatform = new Platform(this, getImage(info.getPath()), info);

				PLATFORM.add(myPlatform);

			}
		}

		// set up collision groups
		playfield.addCollisionGroup(CHARACTER, PROJECTILE,
				new CharacterProjectileCollision());
		playfield.addCollisionGroup(PROJECTILE, BAD_GUYS,
				new EnemyProjectileCollision());
		playfield.addCollisionGroup(CHARACTER, PLATFORM,
				new SpritePlatformCollision());
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

}

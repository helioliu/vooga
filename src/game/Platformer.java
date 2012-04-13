package game;
import interactiveSprites.InteractiveSprite;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
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

import core.EventManager;

public class Platformer extends GameObject {

	public PlayField playfield;

	public Background background;
	public SpriteGroup CHARACTER, PROJECTILE, POWER_UP, PLATFORM, SPAWNPOINT,
			COINS, BAD_GUYS, INTERACTIVE_SPRITES, EXIT;


	private PlatformGame game;

	
	public Platformer(PlatformGame parent) {
		super(parent);
		// TODO Auto-generated constructor stub
		game = parent;
	}



	@Override
	public void initResources() {
		playfield = new LevelBuilder(this).createLevel(PlatformGame.LEVEL_FILES
				.get(PlatformGame.currentLevel));

	}

	@Override
	public void update(long arg0) {
		playfield.update(arg0);
		
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
//			revertPosition1();
//			if (collisionSide == BOTTOM_TOP_COLLISION) {
//				s1.setVerticalSpeed(0);
//				((Character) s1).jumping = false; 
//			}
//			if (collisionSide == LEFT_RIGHT_COLLISION
//					|| collisionSide == RIGHT_LEFT_COLLISION) {
//				s1.setHorizontalSpeed(0);
//			}
			EventManager.getEventManager().sendEvent("floor collide");
		}
	}
	
	class CharacterInteractiveSpriteCollision extends AdvanceCollisionGroup {

		@Override
		public void collided(Sprite s1, Sprite s2) {
			
			((InteractiveSprite) s2).primaryAction(this);
			
		}
	}

}

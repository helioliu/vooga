package levelEditor;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.Timer;


public class Character extends Sprite {

	Platfomer game;
	boolean enableGunFire;
	boolean enableFireball;
	boolean jumping;
	private Timer jumpTimer;

	public Character(BufferedImage image) {
		super(image, 0, 0);
		enableGunFire = true;
		enableFireball = true;
		jumpTimer = new Timer(150);

	}

	public Character(Platfomer currentGame, BufferedImage image, SpriteInfo info) {
		super(image, info.getX(), info.getY());
		game = currentGame;
		enableGunFire = info.getT1();
		enableFireball = info.getT2();
		jumpTimer = new Timer(150);


	}

	public Character(Character character, Platfomer currentGame) {
		super(character.getImage(), 0, 0);
		game = currentGame;
	}

	@Override
	public void update(long elapsedTime) {
		if (jumpTimer.isActive() && jumpTimer.action(elapsedTime)) {
			jumpTimer.setActive(false);
		}
		detectKeyboardEvent(elapsedTime);
		super.update(elapsedTime);
	}

	private void detectKeyboardEvent(long elapsedTime) {
		double maxSpeed = 0.2;

		if (game.keyDown(KeyEvent.VK_LEFT)) {
			addHorizontalSpeed(elapsedTime, -0.002, -maxSpeed);

		} else if (game.keyDown(KeyEvent.VK_RIGHT)) {
			addHorizontalSpeed(elapsedTime, 0.002, maxSpeed);

		} else {
			if (getHorizontalSpeed() > 0) {
				addHorizontalSpeed(elapsedTime, -0.05, 0);
			} else if (getHorizontalSpeed() < 0) {
				addHorizontalSpeed(elapsedTime, 0.05, 0);
			} else {
				setHorizontalSpeed(0);
			}
		}
		if (game.keyDown(KeyEvent.VK_UP) || game.keyDown(KeyEvent.VK_SPACE)) {
			if (!jumping && getVerticalSpeed() == 0) {
				jumping = true;
				setVerticalSpeed(-0.75);
				jumpTimer.setActive(true);
			}
		}

		if (game.keyDown(KeyEvent.VK_Z) && enableGunFire) {
			// firing missile
			Sprite projectile = new Sprite(game.getImage("emissle1.png"),
					this.getX() + this.width, this.getY() + 15);
			projectile.setHorizontalSpeed(0.7);
			game.PROJECTILE.add(projectile);

			// give time to our fighter to reload ammunition :)
		}

		// try to fire when space key pressed
		if (game.keyDown(KeyEvent.VK_X) && enableFireball) {
			// firing missile
			Sprite projectile = new Sprite(game.getImage("fireball.png"),
					this.getX() + this.width, this.getY() + 15);
			projectile.setHorizontalSpeed(0.7);
			game.PROJECTILE.add(projectile);

			// give time to our fighter to reload ammunition :)
		}

		addVerticalSpeed(elapsedTime, 0.002, 0.5);
	}
}

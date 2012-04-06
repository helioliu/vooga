package sprites;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import levelEditor.*;
import game.*;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.Timer;


public class Character extends Sprite implements LevelEditable {

	Platfomer game;
	boolean enableGunFire=true;
	boolean enableFireball=true;
	public boolean jumping=true;
	private Timer jumpTimer;
	String path;

	public Character() {
		super(null,0,0);
        jumpTimer = new Timer(150);
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

	public ArrayList<Object> writableObject() {
		ArrayList<Object> o= new ArrayList<Object>();
		o.add(path);
		o.add(this.getX());
		o.add(this.getY());
		o.add(enableGunFire);
		o.add(jumping);
		o.add(enableFireball);
		return o;
	}


	public void parse(ArrayList<Object> o, Platfomer myGame) {
		game=myGame;
		path=(String) o.get(0);
		this.path=path;
		File file= new File(path);
		BufferedImage image;
		try {
			image = ImageIO.read(file);

		setImage(image);		} catch (IOException e) {
			e.printStackTrace();
		}
		setX( (Integer) o.get(1));
		setY((Integer) o.get(2));
		enableGunFire= (Boolean) o.get(3);
		jumping = (Boolean) o.get(4);
		enableFireball = (Boolean) o.get(5);
		game.CHARACTER.add(this);
		
	}
	

	public void setInitX(double d) {
		this.setX(d);
		
	}

	public void setInitY(double val) {
		this.setY(val);
		
	}

	public void setInitPath(String path) {
		this.path=path;
		
	}
}

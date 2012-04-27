package sprites;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;


import game.*;

import stateManagers.StateManager;

import States.StationaryState;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.Timer;


public class Character extends StateSprite {


    public boolean jumping=true;
    private Timer jumpTimer;

    public Character() {
        super();
        jumpTimer = new Timer(150);
        getStateManager().addState(new StationaryState(this));
        setGravity(0.002);
    }

    @Override
    public void update(long elapsedTime) {
        //		if (jumpTimer.isActive() && jumpTimer.action(elapsedTime)) {
        //			jumpTimer.setActive(false);
        //		}
        //		detectKeyboardEvent(elapsedTime);
        super.update(elapsedTime);
        this.addVerticalSpeed(elapsedTime, 0.002, 0.5);
    }

    //	private void detectKeyboardEvent(long elapsedTime) {
    //		double maxSpeed = 0.2;
    //
    //		if (game.keyDown(KeyEvent.VK_LEFT)) {
    //			addHorizontalSpeed(elapsedTime, -0.002, -maxSpeed);
    //
    //		} else if (game.keyDown(KeyEvent.VK_RIGHT)) {
    //			addHorizontalSpeed(elapsedTime, 0.002, maxSpeed);
    //
    //		} else {
    //			if (getHorizontalSpeed() > 0) {
    //				addHorizontalSpeed(elapsedTime, -0.05, 0);
    //			} else if (getHorizontalSpeed() < 0) {
    //				addHorizontalSpeed(elapsedTime, 0.05, 0);
    //			} else {
    //				setHorizontalSpeed(0);
    //			}
    //		}
    //		if (game.keyDown(KeyEvent.VK_UP) || game.keyDown(KeyEvent.VK_SPACE)) {
    //			if (!jumping && getVerticalSpeed() == 0) {
    //				jumping = true;
    //				setVerticalSpeed(-0.75);
    //				jumpTimer.setActive(true);
    //			}
    //		}
    //
    //		if (game.keyDown(KeyEvent.VK_Z) && enableGunFire) {
    //			// firing missile
    //			Sprite projectile = new Sprite(game.getImage("emissle1.png"),
    //					this.getX() + this.width, this.getY() + 15);
    //			projectile.setHorizontalSpeed(0.7);
    //			game.PROJECTILE.add(projectile);
    //
    //			// give time to our fighter to reload ammunition :)
    //		}
    //
    //		// try to fire when space key pressed
    //		if (game.keyDown(KeyEvent.VK_X) && enableFireball) {
    //			// firing missile
    //			Sprite projectile = new Sprite(game.getImage("fireball.png"),
    //					this.getX() + this.width, this.getY() + 15);
    //			projectile.setHorizontalSpeed(0.7);
    //			game.PROJECTILE.add(projectile);
    //
    //			// give time to our fighter to reload ammunition :)
    //		}
    //
    //		addVerticalSpeed(elapsedTime, 0.002, 0.5);
    //	}

  
}
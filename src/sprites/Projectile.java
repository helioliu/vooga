package sprites;
import java.awt.image.BufferedImage;

import levelEditor.*;
import game.*;

import com.golden.gamedev.object.Sprite;


public class Projectile extends Sprite {
	// indicates whether this enemy has been show to screen or not
	boolean show = false;
	Platformer game;


	

	public Projectile(BufferedImage image) {
		super(image,0,0);
		
	}

	public Projectile(Projectile projectile, Platformer currentGame) {
		super(projectile.getImage(),0,0);
		show=projectile.show;
		game=currentGame;
	}
	
	public void home(Sprite target){
	double angleToTarget= getAngle(target.getHorizontalSpeed(), target.getVerticalSpeed());
	double velocity = getVelocity(target.getHorizontalSpeed(), target.getVerticalSpeed());
	this.setMovement(velocity, angleToTarget);
	}
	
	public static double getAngle(double xVelocity, double yVelocity){
	    return Math.toDegrees(Math.atan2(yVelocity, xVelocity));
	}
	
	public static double getVelocity(double xVelocity, double yVelocity){
	    return Math.sqrt(Math.pow(xVelocity, 2)+ Math.pow(yVelocity, 2));
	}
	
	
	
}

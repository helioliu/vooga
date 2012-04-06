package sprites;
import java.awt.image.BufferedImage;

import levelEditor.*;
import game.*;

import com.golden.gamedev.object.Sprite;


public class Projectile extends Sprite {
	// indicates whether this enemy has been show to screen or not
	boolean show = false;
	Platfomer game;
	// attempt to fire every 400 ms

	

	public Projectile(BufferedImage image) {
		super(image,0,0);
		
	}

	public Projectile(Projectile projectile, Platfomer currentGame) {
		super(projectile.getImage(),0,0);
		show=projectile.show;
		game=currentGame;
	}
	
	
	
}

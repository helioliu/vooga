package levelEditor;
import java.awt.image.BufferedImage;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.Timer;


public class Bad_Guys extends Sprite {
	// indicates whether this enemy has been show to screen or not
	boolean show = true;
	boolean enableShoot;
	Platfomer game;
	// attempt to fire every 400 ms
	Timer	fireRate = new Timer(4000);
	

	public Bad_Guys(BufferedImage image) {
		super(image,0,0);
		
	}

	public Bad_Guys(Platfomer currentGame, BufferedImage image, SpriteInfo info) {
		super(image,info.getX(),info.getY());
		game=currentGame;
		enableShoot=info.getT1();
	}
	
	@Override
	public void update(long elapsedTime) {
		super.update(elapsedTime);
		

		if (show==true){
		if (fireRate.action(elapsedTime)) { // random
			// fire!!

	     Sprite projectile = new Sprite(game.getImage("emissle1.png"),
					this.getX(), this.getY()+15);
         projectile.setHorizontalSpeed(-0.7);
          game.PROJECTILE.add(projectile);
		}

		}
	}
}

package sprites;
import java.awt.image.BufferedImage;

import levelEditor.Platfomer;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.Timer;


public class Exit extends Sprite {

	// indicates whether this enemy has been show to screen or not
	boolean show = false;
	Platfomer game;
	// attempt to fire every 400 ms
	Timer	fireRate = new Timer(400);
	

	public Exit(BufferedImage image) {
		super(image,0,0);
		
	}

	public Exit(Exit exit, Platfomer currentGame) {
		super(exit.getImage(),0,0);
		fireRate=exit.fireRate;
		show=exit.show;
		game=currentGame;
	}
	
	@Override
	public void update(long elapsedTime) {
		super.update(elapsedTime);
		
		}
}

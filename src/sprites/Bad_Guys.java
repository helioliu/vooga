package sprites;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import levelEditor.Platfomer;
import levelEditor.SpriteInfo;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.Timer;


public class Bad_Guys extends PlatformSprite {
	// indicates whether this enemy has been show to screen or not
	boolean show;
	boolean enableShoot;
	Platfomer myGame;
	// attempt to fire every 400 ms
	Timer	fireRate = new Timer(4000);
	

	public Bad_Guys() {
		super();
		
	}


	
	@Override
	public void update(long elapsedTime) {
		super.update(elapsedTime);
		

		if (show==true){
		if (fireRate.action(elapsedTime)) { // random
			// fire!!

	     Sprite projectile = new Sprite(myGame.getImage("emissle1.png"),
					this.getX(), this.getY()+15);
         projectile.setHorizontalSpeed(-0.7);
          myGame.PROJECTILE.add(projectile);
		}

		}
	}


	public ArrayList<Object> writableObject() {
		ArrayList<Object> o= new ArrayList<Object>();
		o.add(path);
		o.add(x);
		o.add(y);
		o.add(enableShoot);
		return o;
	}


	public void parse(ArrayList<Object> o, Platfomer game) {
		myGame=game;
		path=(String) o.get(0);
		x= (Integer) o.get(1);
		y= (Integer) o.get(2);
		enableShoot= (Boolean) o.get(3);
	}
}

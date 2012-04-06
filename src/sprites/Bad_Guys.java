package sprites;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


import levelEditor.*;
import game.*;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.Timer;


public class Bad_Guys extends Sprite implements LevelEditable {
	// indicates whether this enemy has been show to screen or not
	String path;
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
		o.add(getX());
		o.add(getY());
		o.add(enableShoot);
		return o;
	}


	public void parse(ArrayList<Object> o, Platfomer game) {
		myGame=game;
		path=(String) o.get(0);
		setX( (Integer) o.get(1));
		setY( (Integer) o.get(2));
		enableShoot= (Boolean) o.get(3);
		myGame.BAD_GUYS.add(this);
	}



	public void setInitX(double d) {
		// TODO Auto-generated method stub
		
	}



	public void setInitY(double val) {
		// TODO Auto-generated method stub
		
	}



	public void setInitPath(String path) {
		this.path=path;
		
	}
}

package sprites;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;


import levelEditor.*;
import game.*;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.Timer;


public class Bad_Guys extends LESprite implements LevelEditable {
	// indicates whether this enemy has been show to screen or not
	String path;
	boolean show;
	boolean enableShoot;
	Platformer myGame;
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
         // myGame.PROJECTILE.add(projectile);
		}

		}
	}


	public ArrayList<String> writableObject() {
		ArrayList<String> list= new ArrayList<String>();
		list.add(this.getClass().toString());
		list.add(path);
		list.add(getX() +"");
		list.add(getY() +"");
		return list;
	}


	public Sprite parse(ArrayList<String> o, Platformer game) {
        Bad_Guys BG= new Bad_Guys();
		BG.path=o.get(1);
		BG.setX( Double.parseDouble(o.get(2)));
		BG.setY( Double.parseDouble(o.get(3)));
		BG.enableShoot= true;
		File file= new File(path);
		BufferedImage image;
		try {
			image = ImageIO.read(file);
			BG.setImage(image);		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		return BG;
	}

	public Boolean isInstanceOf(ArrayList<String> o) {
		if (this.getClass().toString().equals(o.get(0))) {
			return true;
		}
		return false;
	}
}

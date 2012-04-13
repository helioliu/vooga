package sprites;

import game.Platformer;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import StateMachines.CharacterStateMachine;
import StateMachines.StateMachine;

import com.golden.gamedev.object.Sprite;

public class Chris_TestSprite extends GeneralSprite{
	
	
	public Chris_TestSprite()
	{
		super();
		myStateMachine = new CharacterStateMachine(((Sprite) this));
		myGravityValue = 0.002;
	}
	
	

	public void update(long elapsedTime)
	{
		super.update(elapsedTime);
		this.addVerticalSpeed(elapsedTime, 0.002, 0.5);
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
        Chris_TestSprite C= new Chris_TestSprite();
        C.mygame=game;
		C.path=o.get(1);
		C.setX( Double.parseDouble(o.get(2)));
		C.setY( Double.parseDouble(o.get(3)));
		File file= new File(path);
		BufferedImage image;
		try {
			image = ImageIO.read(file);
			C.setImage(image);		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		mygame.CHARACTER.add(C);
		return C;
	}


	public Boolean isInstanceOf(ArrayList<String> o) {
		if (this.getClass().toString().equals(o.get(0))) {
			return true;
		}
		return false;
	}

}

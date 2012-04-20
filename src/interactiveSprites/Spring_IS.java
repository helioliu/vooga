package interactiveSprites;
import game.Platformer;

import interactiveSprites.interactiveExample.RPGGame;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import sprites.GeneralSprite;
import sprites.LevelEditable;
import stateManagers.StateManager;

import States.StationaryState;

import com.golden.gamedev.GameObject;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.CollisionGroup;
import com.sun.corba.se.spi.orbutil.fsm.State;

@SuppressWarnings("serial")
public class Spring_IS extends GeneralSprite implements LevelEditable, InteractiveSprite {
	
	Platformer myGame;
	String path;
	String myType;
	StateManager myStateManager;
	
	
	public Spring_IS(BufferedImage bufferedImage, int i, int j, Platformer game) {
		super(bufferedImage, i, j);
		myType = "spring";
		myGame = game;
		myStateManager = new StateManager(this, new StationaryState(this));
	}
	
	public Spring_IS() {

	}

	public void primaryAction(CollisionGroup c) {
		if(c.getCollisionSide()== c.BOTTOM_TOP_COLLISION) {
			myGame.CHARACTER.getActiveSprite().setVerticalSpeed(-1.25);
		}
		if(c.getCollisionSide()== c.TOP_BOTTOM_COLLISION) {
			
		}
		if(c.getCollisionSide()== c.LEFT_RIGHT_COLLISION) {
			
		}
		if(c.getCollisionSide()== c.RIGHT_LEFT_COLLISION) {
			
		}
	}
	
	public void userMove() {
		this.setX(myGame.CHARACTER.getActiveSprite().getX() + this.width);
		this.setY(myGame.CHARACTER.getActiveSprite().getY());
	}
	
	public String getType() {
		return myType;
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
			Spring_IS s= new Spring_IS();
			s.path=o.get(1);
			s.setX( Double.parseDouble(o.get(2)));
			s.setY( Double.parseDouble(o.get(3)));
			File file= new File(path);
			BufferedImage image;
			try {
				image = ImageIO.read(file);
				s.setImage(image);		} 
			catch (IOException e) {
				e.printStackTrace();
			}
			return s;
		
	}


		@Override
	public Boolean isInstanceOf(ArrayList<String> o) {
			if (this.getClass().toString().equals(o.get(0))) {
				return true;
			}
			return false;
	}
	

}

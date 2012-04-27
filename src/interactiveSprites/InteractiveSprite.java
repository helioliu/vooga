package interactiveSprites;

import game.Platformer;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import sprites.GeneralSprite;
import stateTransitions.ChangeStateTransition;
import stateTransitions.StateTransition;

import States.InteractiveSpriteStates.CarryingState;
import States.InteractiveSpriteStates.TouchingState;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.CollisionGroup;

@SuppressWarnings("serial")
public abstract class InteractiveSprite extends GeneralSprite {
	
	String path;
	String myType;
	
	public InteractiveSprite() {
		
		StateTransition collide = new ChangeStateTransition(getStateManager(), "ISCollision", new TouchingState((this)));
		collide.activate();
		
		StateTransition carrying = new ChangeStateTransition(getStateManager(),"toCarrying", new CarryingState(this));
		carrying.activate();
		
	}
	
	/**
	 * performs primary function of the interactive sprite type
	 */
	public abstract void primaryAction(CollisionGroup c, GeneralSprite s);
	
	public abstract void throwAction();
	
	/**
	 * returns type of interactive sprite
	 */
	
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

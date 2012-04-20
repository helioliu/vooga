package sprites;

import game.Platformer;
import hudDisplay.Stat;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;


import stateManagers.StateManager;
import stateTransitions.ChangeStateTransition;
import stateTransitions.StateTransition;

import States.OnLandState;
import States.RegularMotionState;
import States.ReverseMotionState;
import States.WalkingRightState;

import com.golden.gamedev.object.Sprite;

public class Chris_TestSprite extends GeneralSprite{
	
	
	public Chris_TestSprite()
	{
		super();
		setStateManager(new StateManager(((Sprite) this), new RegularMotionState(this)));
		StateTransition reverse = new ChangeStateTransition(getStateManager(), "switchstates", new RegularMotionState(this));
		setMyStats(new HashMap<String, Stat>());	
		reverse.activate();
		setGravity(0.00);
	}
	
	

	public void update(long elapsedTime)
	{
		super.update(elapsedTime);
		this.addVerticalSpeed(elapsedTime, getGravity(), 0.5);
	}



	public ArrayList<String> writableObject() {
		ArrayList<String> list= new ArrayList<String>();
		list.add(this.getClass().toString());
		list.add(getPath());
		list.add(getX() +"");
		list.add(getY() +"");
		return list;
	}


	public Sprite parse(ArrayList<String> o, Platformer game) {
        Chris_TestSprite C= new Chris_TestSprite();
        C.setMygame(game);
		C.setInitPath(o.get(1));
		C.setX( Double.parseDouble(o.get(2)));
		C.setY( Double.parseDouble(o.get(3)));
		File file= new File(getPath());
		BufferedImage image;
		try {
			image = ImageIO.read(file);
			C.setImage(image);		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		getMygame().CHARACTER.add(C);
		return C;
	}


	public Boolean isInstanceOf(ArrayList<String> o) {
		if (this.getClass().toString().equals(o.get(0))) {
			return true;
		}
		return false;
	}

}

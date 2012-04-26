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
import stateTransitions.ReplaceStateTransition;
import stateTransitions.StateTransition;
import stateTransitions.AlternateStatesTransition;
import stateTransitions.AddStateTransition;

import States.InAirState;
import States.OnLandState;
import States.RegularMotionState;
import States.ReverseMotionState;
import States.State;
import States.TeleJumpPowerup;
import States.WalkingRightState;

import com.golden.gamedev.object.Sprite;

public class Chris_TestSprite extends GeneralSprite{
	
	
	public Chris_TestSprite()
	{
		super();

		State s1 = new InAirState(this);
		setGravity(0.000); //.002
		getStateManager().addState(s1);
		StateTransition land = new ReplaceStateTransition(getStateManager(), "floor collide",  new OnLandState(this), s1);
		StateTransition jump = new ReplaceStateTransition(getStateManager(), "jumped", s1, new OnLandState(this));
		StateTransition powerup = new AddStateTransition(getStateManager(), "pwrup", new TeleJumpPowerup(this));
		setMyStats(new HashMap<String, Stat>());	
		land.activate();
		jump.activate();
		powerup.activate();
		
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

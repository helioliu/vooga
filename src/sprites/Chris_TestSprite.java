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
import States.JetPackPowerup;
import States.WalkingRightState;

import com.golden.gamedev.object.Sprite;

public class Chris_TestSprite extends StateSprite{
	
	
	public Chris_TestSprite()
	{
		super();

		State s1 = new InAirState(this);
		setGravity(0.002); //.002
		getStateManager().addState(s1);
		StateTransition land = new ReplaceStateTransition(getStateManager(), "landed",  new OnLandState(this), s1);
		StateTransition jump = new ReplaceStateTransition(getStateManager(), "jumped", s1, new OnLandState(this));
		StateTransition powerup = new AddStateTransition(getStateManager(), "pwrup", new JetPackPowerup(this));
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





}

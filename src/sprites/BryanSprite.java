
	package sprites;
	import game.Platformer;
	import hudDisplay.Stat;
	import java.awt.image.BufferedImage;
	import java.io.File;
	import java.io.IOException;
	import java.util.ArrayList;
	import java.util.HashMap;
	import javax.imageio.ImageIO;
	import stateManagers.StateManager;
	import stateTransitions.ChangeStateTransition;
	import stateTransitions.StateTransition;
import States.InAirState;
	import States.RegularMotionState;
	import States.State;
import com.golden.gamedev.object.Sprite;

	public class BryanSprite extends StateSprite{
		
		
		public BryanSprite()
		{
			super();
			State s = new RegularMotionState(this);
<<<<<<< HEAD
			setStateManager(new StateManager(this, s));
			StateTransition land = new ChangeStateTransition(getStateManager(), "landed", new RegularMotionState(this));
			StateTransition jump = new ChangeStateTransition(getStateManager(), "jumped", new InAirState(this));
=======
			getStateManager().addState(s);
			StateTransition reverse = new ChangeStateTransition(getStateManager(), "switchstates", new OnLandState(this));
>>>>>>> d2dc27f73248067dcb8772887727c0d7a2726b54
			setMyStats(new HashMap<String, Stat>());	
			land.activate();
			jump.activate();
			setGravity(0.00);
		}
		
		

		public void update(long elapsedTime)
		{
			super.update(elapsedTime);
			this.addVerticalSpeed(elapsedTime, getGravity(), 0.5);
		}



		

	}


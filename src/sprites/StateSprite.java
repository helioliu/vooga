package sprites;

import java.awt.image.BufferedImage;

import stateManagers.StateManager;

public class StateSprite extends GeneralSprite{

	private StateManager myStateManager;
	public StateSprite() {
		super();
		myStateManager = new StateManager(this);
	}
	
	public StateSprite(BufferedImage image){
	   super(image); 
	   myStateManager = new StateManager(this);
	}
	
	public StateSprite(BufferedImage[] images, double x, double y) {
        super(images, x, y);
        myStateManager = new StateManager(this);
        
    }

    public StateManager getStateManager()
	{
		return myStateManager;
	}

}

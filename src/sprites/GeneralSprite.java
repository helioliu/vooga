package sprites;

import game.Platformer;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import stateManagers.StateManager;


import collisions.Hitbox;

import com.golden.gamedev.object.sprite.AdvanceSprite;

public abstract class GeneralSprite extends AdvanceSprite implements Boxable, LevelEditable {

	protected Map<String, Integer> myScores;
	protected StateManager myStateMachine;
	protected List<Hitbox> myHitboxes;
	protected double myGravityValue; 
	protected Platformer mygame;
	protected String path;
	protected int x;
	protected int y;

	
	
	public GeneralSprite() {
		super();
	}
	
	public GeneralSprite(BufferedImage i) {
		super();
		BufferedImage[] image = new BufferedImage[1];
		image[0] = i;
		setImages(image);
	}
	
	public GeneralSprite(BufferedImage[] i) {
		super(i);
	}
	
	public GeneralSprite(BufferedImage i, double x, double y) {
		super(x,y);
		setImage(i);
	}
	
	public GeneralSprite(BufferedImage[] i, double x, double y) {
		super(i,x,y);
	}
	
	public GeneralSprite(double x, double y) {
		super(x,y);
	}

	public List<Hitbox> getHitboxes() {
		return myHitboxes;
	}

	public String getDefaultEvent() {
		return "";
	}
	public void setGravity(double d)
	{
		myGravityValue = d;
	}
	
	public void setImage(BufferedImage i) {
		BufferedImage[] image = new BufferedImage[1];
		image[0] = i;
		setImages(image);
	}
	public void setInitX(double d) {
		x = (int) d;
		this.setX(x);
		
	}

	public void setInitY(double val) {
		y = (int) val;
		this.setY(y);
		
	}

	public void setInitPath(String path) {
		this.path=path;
		
	}
	
	

}

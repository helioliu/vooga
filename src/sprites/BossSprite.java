package sprites;

import game.Platformer;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.awt.Graphics2D;

import collisions.Hitbox;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.AdvanceSpriteGroup;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.Timer;


import com.golden.gamedev.object.Sprite;

public class BossSprite extends GeneralSprite{
	
	private static String myPictures = "images/testboss.png";
	//private BufferedImage[] bi = getImages(myPictures, 2, 2);
	
	public static final int LEFT = 0;
	public static final int RIGHT = 1;
	public static final int UP = 2;
	public static final int DOWN = 3;
	
	public static final int STANDING = 0;
	public static final int MOVING = 1;
	
	private static final int[][] movingAnimation = 
			new int[][] { {0, 1, 0, 1}, {2, 0, 2, 0}, {0, 3, 0, 3},
						  {0, 1, 0, 1} };
	
	
	
	
	
	
	
	public BossSprite(BufferedImage[] images, int x, int y){
		//BufferedImage[] derp = getImages(myPictures, 2, 2);
		super(images, x, y);
		setAnimation(STANDING, LEFT);
		getAnimationTimer().setDelay(500);
		setAnimate(true);
		setLoopAnim(true);
	}
	
	public List<Hitbox> getHitboxes() {
		return new ArrayList<Hitbox>();
	}
	
	
	public void update(long elapsedTime){
		super.update(elapsedTime);
		
		if(getStatus()==MOVING){
			setStatus(STANDING);
			setFrame(getFrame()+1);
		}
		
		if(getStatus()==STANDING){
			setStatus(MOVING);
			setFrame(getFrame()+1);
			//updateLogic(elapsedTime);
		}
	}
	
	public boolean walkTo(int dir, int horiz, int vert){
		return true;
	}
	
	protected void animationChanged(int oldStat, int oldDir, int status, int direction){
		setAnimationFrame(movingAnimation[getStatus()]);
	}
	
	public void render(Graphics2D g){
		if(getImages() != null)
			super.render(g);
	}
	
	
	

	@Override
	public ArrayList<String> writableObject() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Sprite parse(ArrayList<String> o, Platformer myGame) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean isInstanceOf(ArrayList<String> o) {
		// TODO Auto-generated method stub
		return null;
	}

}

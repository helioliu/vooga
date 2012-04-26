package sprites;

import game.Platformer;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.awt.Graphics2D;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.Timer;

import collisions.CollisionRect;
import collisions.Hitbox;

public class BossSprite extends GeneralSprite{
	
	public static final int LEFT = 0;
	public static final int RIGHT = 1;
	public static final int UP = 2;
	public static final int DOWN = 3;
	
	public static final int STANDING = 0;
	public static final int MOVING = 1;
	public static final int PUNCHING = 2;
	public static final int CROUCHING = 3;
	
	private static final int[][] movingAnimation = 
			new int[][] { {0}, {1}, {3},
						  {2} };
	
	private Timer t = new Timer (2000);
	
	private List<Hitbox> standingHitbox = new ArrayList<Hitbox>();
	private List<Hitbox> movingHitbox = new ArrayList<Hitbox>();
	private List<Hitbox> punchingHitbox = new ArrayList<Hitbox>();
	private List<Hitbox> crouchingHitbox = new ArrayList<Hitbox>();
	
	
	public BossSprite(BufferedImage[] images, int x, int y){
		//BufferedImage[] derp = getImages(myPictures, 2, 2);
		super(images, x, y);
		setAnimation(PUNCHING, LEFT);
		//getAnimationTimer().setDelay(500);
		setAnimate(true);
		setLoopAnim(true);
		setID(9999);
		standingHitbox.add(new Hitbox(new CollisionRect(0, 0, 4*82, 92), "1"));
		movingHitbox.add(new Hitbox(new CollisionRect(92, 0, 82*4, 92), "2"));
		punchingHitbox.add(new Hitbox(new CollisionRect(92*2, 0, 4*82, 92), "3"));
		crouchingHitbox.add(new Hitbox(new CollisionRect(92*3, 0, 4*82, 92), "4"));
		Hitbox hb = new Hitbox(new CollisionRect(92*3+1, 0, 82*4, 2), "5");
		standingHitbox.add(hb);
		movingHitbox.add(hb);
		punchingHitbox.add(hb);
		crouchingHitbox.add(hb);
	}
	
	public List<Hitbox> getHitboxes() {
		switch(getStatus()){
		case 0:
			return standingHitbox;
		case 1:
			return movingHitbox;
		case 2:
			return punchingHitbox;
		case 3:
			return crouchingHitbox;
		}
		return new ArrayList<Hitbox>();
	}
	
	
	public void update(long elapsedTime){
		super.update(elapsedTime);
		
		if(t.action(elapsedTime)){
			if(getStatus()==STANDING)
				setStatus(MOVING);
			else
			if(getStatus()==MOVING)
				setStatus(PUNCHING);
			else
			if(getStatus()==PUNCHING)
				setStatus(CROUCHING);
			else
			if(getStatus()==CROUCHING)
				setStatus(STANDING);
			
			
		}
		
	}
	
	protected void animationChanged(int oldStat, int oldDir, int status, int direction){
		setAnimationFrame(movingAnimation[getStatus()]);
	}
	
	public void render(Graphics2D g){
		if(getImages() != null)
			super.render(g);
	}


	
}

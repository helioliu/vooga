package sprites;

import hudDisplay.NumberStat;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.awt.Graphics2D;

import stateTransitions.ChangeStateTransition;
import stateTransitions.StateTransition;

import States.Angry;
import States.Calm;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.Timer;


import collisions.CollisionCirc;
import collisions.CollisionRect;
import collisions.Hitbox;

public class BossSprite extends StateSprite{
	
	//facing sides
	public static final int LEFT = 0;
	public static final int RIGHT = 1;
	public static final int UP = 2;
	public static final int DOWN = 3;
	
	//statuses
	public static final int STANDING = 0;
	public static final int HAMMER1 = 1;
	public static final int HAMMER2 = 2;
	public static final int SICKLE1 = 3;
	public static final int SICKLE2 = 4;
	
	private static final int[][] movingAnimation = 
			new int[][] { {0}, {1}, {2}, {3}, {4} };
	
	private Timer patternTimer;
	private int SPEED;
	
	@SuppressWarnings("unchecked")
	private List<Hitbox>[] myHitboxes = (ArrayList<Hitbox>[])new ArrayList[]
			{new ArrayList<Hitbox>(), new ArrayList<Hitbox>(),new ArrayList<Hitbox>(),
			new ArrayList<Hitbox>(),new ArrayList<Hitbox>()};
	
	private BufferedImage myProjectileImage;
	private SpriteGroup myProjectileGroup;
	private Sprite myTarget;
	
	
	public BossSprite(BufferedImage[] images, double x, double y){
		super(images, x, y);
		
		setAnimation(STANDING, LEFT);
		setAnimate(true);
		setLoopAnim(true);
		setID(8055);
		this.createStat("health", new NumberStat(200));
		getStateManager().addState(new Calm(this));
		SPEED=1;
		patternTimer = new Timer(2000);

		
		
		
		myHitboxes[0].add(new Hitbox(new CollisionRect(130, 110, 60, 70), "head"));
		
		myHitboxes[1].add(new Hitbox(new CollisionRect(133, 357-244, 60, 70), "head"));
		myHitboxes[1].add(new Hitbox(new CollisionCirc(149, 303-244, 45), "hammer"));
		myHitboxes[2].add(new Hitbox(new CollisionRect(129, 566-244*2, 55, 70), "head"));
		myHitboxes[2].add(new Hitbox(new CollisionCirc(14, 586-244*2, 75), "hammer"));
		
		myHitboxes[3].add(new Hitbox(new CollisionRect(130, 844-244*3, 55, 66), "head"));
		myHitboxes[3].add(new Hitbox(new CollisionRect(207, 824-244*3, 32, 63), "sickle"));
		myHitboxes[4].add(new Hitbox(new CollisionRect(128, 1088-244*4, 56, 64), "head"));
		myHitboxes[4].add(new Hitbox(new CollisionCirc(73, 1140-244*4, 40), "sickle"));
	}
	
	public void setSpeed(int i) {
        SPEED=i;
        
    }

    public BossSprite(BufferedImage[] images, double x, double y, BufferedImage projImage, Sprite target, SpriteGroup projGroup){
		this(images, x, y);
		myProjectileImage = projImage;
		myTarget = target;
		myProjectileGroup = projGroup;
		StateTransition one = new ChangeStateTransition(getStateManager(), "get angry", new Angry(this));
        StateTransition two = new ChangeStateTransition(getStateManager(), "get calm", new Calm(this));
        one.activate();
        two.activate();

	}
	
	public List<Hitbox> getHitboxes() {
		return Collections.unmodifiableList(myHitboxes[getStatus()]);
	}
	
	private void shoot(){
		System.out.println("pewpewpew");
		myProjectileGroup.add(new HomingProjectile(myProjectileImage, myTarget, getX()+30, getY()+160));
	}
	
	
	public void update(long elapsedTime){
		super.update(elapsedTime);	
		if(patternTimer.action(elapsedTime*SPEED)){
			if(getStatus()==STANDING)
				setStatus(HAMMER1);
			else
			if(getStatus()==HAMMER1)
				setStatus(HAMMER2);
			else
			if(getStatus()==HAMMER2)
				setStatus(SICKLE1);
			else
			if(getStatus()==SICKLE1)
				setStatus(SICKLE2);
			else
			if(getStatus()==SICKLE2)
				setStatus(STANDING);
			
			
		}
		
	}
	
	protected void animationChanged(int oldStatus, int oldDir, int status, int direction){
		setAnimationFrame(movingAnimation[getStatus()]);
		if(oldStatus==SICKLE1 && status==SICKLE2)
			shoot();
	}
	
	public void render(Graphics2D g){
		if(getImages() != null)
			super.render(g);
	}

    public void setPatternTimer(Timer timer) {
         patternTimer = timer;
        
    }
    public Timer getTimer() {
        return patternTimer;
       
   }
    
    

   



	
}

package characters;

//JFC
import java.awt.Graphics2D;

import com.golden.gamedev.GameObject;
import com.golden.gamedev.object.Timer;
import com.golden.gamedev.object.sprite.AdvanceSprite;

public class UserSprite extends AdvanceSprite {

	public static final int LEFT_KEY = 0, RIGHT_KEY = 1, UP_KEY = 2, JUMP_KEY = 3;

	public static final int STAND = 1, WALKING = 2;
	public static final int LEFT = 1, RIGHT = 2;
	
	public boolean jumping;
	public boolean shiftDown;
	public boolean onMovingPlatform;
	private Timer jumpTimer;
	public int lives, score;
	int[] controls;
	private GameObject currentGameObeject;



	public UserSprite(GameObject owner, int[] controls) {
		super(owner.getImages("resources/images/mario/mariocharpng.png", 3, 2), 0, 0);
		this.controls = controls;
		this.score = 0;
		onMovingPlatform = false;
		lives = 3;
		jumpTimer = new Timer(150);
		resetState();
	}

	public void resetState() {
		setAnimation(STAND, RIGHT);
		setSpeed(0, 0);
		jumping = false;
		jumpTimer.setActive(false);
	}



	@Override
	public void update(long elapsedTime) {		
		if (jumpTimer.isActive() && jumpTimer.action(elapsedTime)) {
			jumpTimer.setActive(false);
		}
		
		detectKeyboardEvent(elapsedTime);
		super.update(elapsedTime);
	}

	private void detectKeyboardEvent(long elapsedTime) {
		double maxSpeed = 0.2;

		if (currentGameObeject.keyDown(controls[LEFT_KEY])) {
			addHorizontalSpeed(elapsedTime, -0.002, -maxSpeed);
			setDirection(LEFT);
			setStatus(WALKING);

		} else if (currentGameObeject.keyDown(controls[RIGHT_KEY])) {
			addHorizontalSpeed(elapsedTime, 0.002, maxSpeed);
			setDirection(RIGHT);
			setStatus(WALKING);

		} else {
			if (getHorizontalSpeed() > 0) {
				addHorizontalSpeed(elapsedTime, -0.05, 0);
			} else if (getHorizontalSpeed() < 0) {
				addHorizontalSpeed(elapsedTime, 0.05, 0);
			} else {
				setStatus(STAND);
			}
		}
		if (currentGameObeject.keyPressed(controls[UP_KEY]) || 
				currentGameObeject.keyPressed(controls[JUMP_KEY])) {
			if (!jumping && getVerticalSpeed() == 0) {
				jumping = true;
				setVerticalSpeed(-0.75);
				jumpTimer.setActive(true);
			}
			if(onMovingPlatform){
				jumping = true;
				setVerticalSpeed(-0.75);
				jumpTimer.setActive(true);
			}
		}
		addVerticalSpeed(elapsedTime, 0.002, 0.5);
	}


	@Override
	public void render(Graphics2D g, int x, int y) {
		super.render(g, x, y);
	}

}


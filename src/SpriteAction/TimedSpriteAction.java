package SpriteAction;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.Timer;

public class TimedSpriteAction extends SpriteAction{
	
	private Timer timer;

	public TimedSpriteAction(Sprite s) {
		super(s);
		timer = new Timer()
	}

	@Override
	public void actionPerformed(String event) {
		// TODO Auto-generated method stub
		
	}

}

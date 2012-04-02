package States;

import com.golden.gamedev.object.Sprite;

public class InAirState implements State{

	@Override
	public void UpKey(Sprite s) {
		
		
	}

	@Override
	public void DownKey(Sprite s) {
		
	}

	@Override
	public void RightKey(Sprite s) {
		s.move(s.getX()+1, s.getY());
		
	}

	@Override
	public void LeftKey(Sprite s) {
		s.move(s.getX()-1, s.getY());	
		
	}

	@Override
	public void SpaceKey(Sprite s) {
		
	}

	@Override
	public void collide() {
		
	}

}

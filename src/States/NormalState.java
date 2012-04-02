package States;

import com.golden.gamedev.object.Sprite;

public class NormalState implements State{

	@Override
	public void UpKey(Sprite s) {
		s.setVerticalSpeed(1);
	}

	@Override
	public void DownKey(Sprite s) {
		s.move(s.getX(), s.getY()+1);
		
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void collide() {
		// TODO Auto-generated method stub
		
	}
	

}

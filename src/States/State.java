package States;

import com.golden.gamedev.object.Sprite;

public interface State {
	
	public abstract void UpKey(Sprite s);
	public abstract void DownKey(Sprite s);
	public abstract void RightKey(Sprite s);
	public abstract void LeftKey(Sprite s);
	public abstract void SpaceKey(Sprite s);
	public abstract void collide();
	

}

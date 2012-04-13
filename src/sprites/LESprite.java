package sprites;

import game.Platformer;

import java.util.ArrayList;

import com.golden.gamedev.object.Sprite;

public abstract class LESprite extends Sprite implements LevelEditable{
	protected String path;
	protected int x;
	protected int y;


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

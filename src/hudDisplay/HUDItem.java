package hudDisplay;

import sprites.TestCharacterWithStates;

import com.golden.gamedev.object.Sprite;

public interface HUDItem {

	public abstract void adjust(int newScore);

	public abstract TestCharacterWithStates getAssociatedSprite();

	public abstract int getItemScore();
	
	public abstract String getScoreID();

}

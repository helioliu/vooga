package hudDisplay;

import sprites.TestCharacterWithStates;

import com.golden.gamedev.object.Sprite;

public interface GraphicItem extends HUDItem{
	
	public abstract void adjust(int newScore);

	public abstract TestCharacterWithStates getAssociatedSprite();

	public abstract int getItemScore();

	public abstract Sprite getSpriteVersion();

}
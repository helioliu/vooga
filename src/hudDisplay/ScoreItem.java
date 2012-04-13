package hudDisplay;

import java.awt.Graphics2D;

import sprites.TestCharacterWithStates;

import com.golden.gamedev.object.GameFont;

public class ScoreItem implements HUDItem{

	private GameFont myScoreFont;
	private int myX;
	private int myY;
	private String myID;
	private TestCharacterWithStates myAssociatedSprite;
	public int myScore;

	public ScoreItem(GameFont scoreFont, int x, int y, String name,TestCharacterWithStates sprite) {
		
		myScoreFont = scoreFont;
		myX = x;
		myY = y;
		myAssociatedSprite = sprite;
		myScore = sprite.getScore(name);
		myID = name;
	}

	@Override
	public TestCharacterWithStates getAssociatedSprite() {
		return myAssociatedSprite;
	}

	@Override
	public int getItemScore() {
		return myScore;
	}
	
	public void render(Graphics2D g){
		myScoreFont.drawString(g, String.valueOf(myScore), 118, 7);
	}

	@Override
	public void adjust(int newScore) {
		myScore = newScore;
	}

	@Override
	public String getScoreID() {
		return myID;
	}

}

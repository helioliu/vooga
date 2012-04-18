package hudDisplay;

import java.awt.Graphics2D;

import sprites.BryanSprite;
import sprites.TestCharacterWithStates;

import com.golden.gamedev.object.GameFont;

public class TextItem implements HUDItem{

	private GameFont myTextFont;
	private int myX;
	private int myY;
	private String myID;
	private BryanSprite myAssociatedSprite;
	public int myText;

	public TextItem(GameFont textFont, int x, int y, String name,BryanSprite s1) {
		
		myTextFont = textFont;
		myX = x;
		myY = y;
		myAssociatedSprite = s1;
		myText = s1.getScore(name);
		myID = name;
	}

	@Override
	public BryanSprite getAssociatedSprite() {
		return myAssociatedSprite;
	}

	@Override
	public int getItemScore() {
		return myText;
	}
	
	public void render(Graphics2D g){
		myTextFont.drawString(g, String.valueOf(myText), 118, 7);
	}

	@Override
	public void adjust(int newScore) {
		myText = newScore;
	}

	@Override
	public String getScoreID() {
		return myID;
	}

}

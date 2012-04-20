package hudDisplay;

import java.awt.Graphics2D;
import com.golden.gamedev.object.GameFont;

public class TextItem extends HUDItem{
	
	private GameFont myScoreFont;
	private int myX;
	private int myY;
	private Stat myStat;
	private int HUDX;
	private int HUDY;

	public TextItem(GameFont scoreFont, int X, int Y, Stat stat) {
		myScoreFont = scoreFont;
		myStat = stat;
		myX = X;
		myY = Y;
	}

	@Override
	public void render(Graphics2D g) {
		myScoreFont.drawString(g, String.valueOf(myStat.getMyValue()), HUDX + myX, HUDY + myY);
		
	}

	@Override
	public void update(int HUDX, int HUDY, long elapsedTime) {
		this.HUDX = HUDX;
		this.HUDY = HUDY;
	}


}

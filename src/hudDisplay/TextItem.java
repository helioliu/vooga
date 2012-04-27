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
	private boolean HUDactive;
	private String myAdditionalInfo;

	public TextItem(GameFont scoreFont, int X, int Y, Stat stat) {
		myScoreFont = scoreFont;
		myStat = stat;
		HUDactive = true;
		myX = X;
		myY = Y;
	}
	
	public TextItem(GameFont scoreFont, int X, int Y, Stat stat, String additionalInfo) {
		myScoreFont = scoreFont;
		myStat = stat;
		HUDactive = true;
		myX = X;
		myY = Y;
		myAdditionalInfo = additionalInfo;
	}

	@Override
	public void render(Graphics2D g) {
		if(isHUDactive()){
		if(myAdditionalInfo != null)
		getMyScoreFont().drawString(g, myAdditionalInfo + String.valueOf((int) getMyStat().getMyValue()), getHUDX() + getMyX(), getHUDY() + getMyY());
		else
		getMyScoreFont().drawString(g,String.valueOf((int) getMyStat().getMyValue()), getHUDX() + getMyX(), getHUDY() + getMyY());

		}
		
	}

	@Override
	public void update(int HUDX, int HUDY, long elapsedTime) {
		this.HUDX = HUDX;
		this.HUDY = HUDY;
	}

	@Override
	public void activateItem(boolean onOff) {
		HUDactive = onOff;
	}

	public boolean isHUDactive() {
		return HUDactive;
	}

	public GameFont getMyScoreFont() {
		return myScoreFont;
	}

	public Stat getMyStat() {
		return myStat;
	}

	public int getHUDX() {
		return HUDX;
	}

	public int getMyX() {
		return myX;
	}

	public int getHUDY() {
		return HUDY;
	}

	public int getMyY() {
		return myY;
	}

}

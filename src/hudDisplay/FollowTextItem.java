package hudDisplay;

import java.awt.Graphics2D;

import sprites.GeneralSprite;

import com.golden.gamedev.object.GameFont;

public class FollowTextItem extends TextItem{

	private GeneralSprite spriteToFollow;
	public FollowTextItem(GameFont scoreFont, int X, int Y, Stat stat, GeneralSprite s1) {
		super(scoreFont, X, Y, stat);
		spriteToFollow = s1;
	}
	
	public void render(Graphics2D g) {
		if(isHUDactive())
		getMyScoreFont().drawString(g, String.valueOf((int) getMyStat().getMyValue()),(int) spriteToFollow.getX() + getMyX(), (int) spriteToFollow.getY() + getMyY());
	}

}

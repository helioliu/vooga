package hudDisplay;

import java.awt.Graphics2D;
import java.util.ArrayList;

import com.golden.gamedev.object.GameFont;
import com.golden.gamedev.object.Sprite;

public class ScoreDecorator extends HUDDecorator {

	HUDInterface hd;
	GameFont scoreFont;
	int score;
	Graphics2D g;

	public ScoreDecorator(HUDInterface hd, GameFont scoreFont, int score,
			int x, int y, Graphics2D g) {
		super(hd);
		this.hd = hd;
		this.scoreFont = scoreFont;
		this.score = score;
		this.g = g;

	}

	@Override
	public ArrayList<Sprite> createWidget() {
		scoreFont.drawString(g, String.valueOf(score), 118, 7);
		return null;
	}

}

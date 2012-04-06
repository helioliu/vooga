package hudDisplay;

import java.awt.Graphics2D;

import com.golden.gamedev.object.GameFont;

public class ScoreDecorator extends HUDDecorator {

	HUDInterface hd;
	GameFont scoreFont;
	int score;
	Graphics2D g;

	public ScoreDecorator(HUDInterface hd, GameFont scoreFont, int score,
			Graphics2D g) {
		super(hd);
		this.hd = hd;
		this.scoreFont = scoreFont;
		this.score = score;
		this.g = g;

	}

	@Override
	public void addToHud() {
		scoreFont.drawString(g, String.valueOf(score), 600, 20);
	}

}

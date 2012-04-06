package hudDisplay;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class HealthDecorator extends HUDDecorator {

	HUDInterface hd;
	BufferedImage healthBar;
	int x;
	int y;
	int initSize;
	Graphics2D g;
	int height;

	public HealthDecorator(HUDInterface hd, BufferedImage healthBar, int x,
			int y, int initSize, int height, Graphics2D g) {
		super(hd);
		this.hd = hd;
		this.healthBar = healthBar;
		this.x = x;
		this.y = y;
		this.initSize = initSize;
		this.g = g;
		this.height = height;
	}

	@Override
	public void addToHud() {

		g.drawImage(healthBar, x, y, initSize, height, null);

	}

}

package hudDisplay;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class HUDDisplay implements HUDInterface {

	HUDInterface hd;
	private Graphics2D g;
	BufferedImage background;
	int x;
	int y;
	int width;
	int height;

	HUDDisplay(HUDInterface hd, BufferedImage background, int x, int y,
			int width, int height, Object objectback, Graphics2D g) {
		this.hd = hd;
		this.background = background;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.g = g;

	}

	@Override
	public void addToHud() {
		g.drawImage(background, x, y, width, height, null);

	}

}
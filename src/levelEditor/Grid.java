package levelEditor;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JLabel;

@SuppressWarnings("serial")
public class Grid extends JLabel {

	public Grid() {
		super();
	}

	public void render(Graphics g, int tileWidth, int tileHeight, int width,
			int height) {
		g.setColor(Color.red);
		for (int i = 0; i < width; i++) {
			g.drawLine(i * tileWidth, 0, i * tileWidth, height * tileHeight);
		}

		for (int j = 0; j < height; j++) {
			g.drawLine(0, j * tileHeight, width * tileWidth, j * tileHeight);
		}
		((Graphics2D) g).setStroke(new BasicStroke(2));
		g.setColor(Color.red);
		g.drawLine(0, 0, width * tileWidth, 0);
		g.drawLine(0, 0, 0, height * tileHeight);
		g.drawLine(width * tileWidth, 0, width * tileWidth, height * tileHeight);
		g.drawLine(0, height * tileHeight, width * tileWidth, height
				* tileHeight);
	}

}

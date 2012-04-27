package levelEditor_gamma;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Tile {

	private int imageWidth = 0;
	private int imageHeight = 0;
	int zoomWidth, zoomHeight;

	String name = null;
	int number = -1;
	String type = null;
	String path = null;
	String info = null;

	Image image = null;

	public Tile(int number, String path, String name, String type) {
		this.type = type;
		this.number = number;
		this.name = name;
		this.path = path;
		this.image = new ImageIcon(path).getImage();

		if (image == null) {
			throw new RuntimeException("Could not load image" + path);
		}

		imageWidth = image.getWidth(null);
		imageHeight = image.getHeight(null);
		zoomWidth = imageWidth;
		zoomHeight = imageHeight;
	}

	public Tile(Tile t) {
		this.number = t.number;
		this.type = t.type;
		this.name = t.name;
		this.path = t.path;
		this.image = t.image;
	}

	String getImageLocation() {
		return path;
	}

	public boolean equals(Tile t) {
		if (t == null)
			return false;
		if (this.number == t.number && this.name.equals(t.name)
				&& this.type == t.type && this.image == t.image) {
			return true;
		} else {
			return false;
		}
	}

	static boolean areEqual(Tile t1, Tile t2) {
		if (t1 == null && t2 == null) {
			return true;
		} else if (t1 != null) {
			return t1.equals(t2);
		} else {
			return false;
		}
	}

	public void render(Graphics g, int x, int y) {
		g.drawImage(image, x - imageWidth, y - imageHeight, null);
	}

}

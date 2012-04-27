package levelEditor;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;
import javax.swing.JComponent;

@SuppressWarnings("serial")
public class Tile extends JComponent implements ImageObserver{

	private int imageWidth = 0;
	private int imageHeight = 0;
	int zoomWidth, zoomHeight;
	String name = null;
	int number = -1;
	String type = null;
	String path = null;
	String info = null;
	Image image = null;

	protected Point anchorPoint;
	protected boolean overbearing = false;
	final Tile handle = this;
	
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

	public Tile(int number, String path, String name, String type, String info) {
		this(number, path, name, type);
		if (info.indexOf(',') >= 0) {
			throw new RuntimeException(
					"Info string cannot contain \",\" characters");
		}
		this.info = info;
	}

	public Tile(Tile t) {
		this.number = t.number;
		this.type = t.type;
		this.name = t.name;
		this.path = t.path;
		this.image = t.image;
	}

	public String getImageLocation() {
		return path;
	}

	public boolean equals(Tile t) {
		if (t == null)
			return false;
		if (this.number == t.number && this.name.equals(t.name) && this.type == t.type && this.image == t.image) {
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

	public Tile() {
		image = null;
	}

	public Image getImage() {
		return image;
	}

	public String getType() {
		return type;
	}

	public int getNumber() {
		return number;
	}

	public String getName() {
		return name;
	}

	public String getInfo() {
		return info;
	}

	public String getPath() {
		return path;
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.clearRect(0, 0, getWidth(), getHeight());
		if (image != null) {
			g2d.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		} else {
			g2d.setColor(getBackground());
			g2d.fillRect(0, 0, getWidth(), getHeight());
		}
	}
	
	@Override
	public boolean imageUpdate(Image img, int infoflags, int x, int y, int w,
			int h) {
		if (infoflags == ALLBITS) {
			repaint();
			return false;
		}
		return true;
	}
}
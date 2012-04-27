package hudDisplay;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.HashSet;
import com.golden.gamedev.object.Sprite;

public class HeadsUpDisplay {

	private int myX;
	private int myY;
	private boolean HUDactive;
	private Sprite myHUDSprite;
	private HashSet<HUDItem> myHUDItems;

	public HeadsUpDisplay(BufferedImage HUDImage, int x, int y) {
		myX = x;
		myY = y;
		myHUDSprite = new Sprite(HUDImage, myX, myY);
		myHUDItems = new HashSet<HUDItem>();
		HUDactive = true;
	}

	public HeadsUpDisplay(int x, int y) {
		myX = x;
		myY = y;
		myHUDItems = new HashSet<HUDItem>();
	}

	public void activeHUD(boolean onOff) {
		for (HUDItem hudItems : myHUDItems) {
			hudItems.activateItem(onOff);
		}
		
		HUDactive = onOff;
		
	}

	public void addItem(HUDItem item) {
		myHUDItems.add(item);
	}

	public HashSet<HUDItem> getHUDItems() {
		return myHUDItems;
	}

	public void render(Graphics2D g) {
		
		if(HUDactive)
		myHUDSprite.render(g);

		for (HUDItem item : myHUDItems) {

			item.render(g);

		}
	}

	public void update(long elapsedTime) {

		myHUDSprite.update(elapsedTime);

		for (HUDItem item : myHUDItems) {

			item.update(myX, myY, elapsedTime);

		}

	}
}

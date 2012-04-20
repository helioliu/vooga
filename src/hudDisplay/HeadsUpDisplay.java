package hudDisplay;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.HashSet;
import com.golden.gamedev.object.Sprite;

public class HeadsUpDisplay {

	private int myX;
	private int myY;
	private Sprite myHUDSprite;
	private HashSet<HUDItem> myHUDItems;
	
	public HeadsUpDisplay(BufferedImage HUDImage, int x, int y) {
		myX = x;
		myY = y;
		myHUDSprite = new Sprite(HUDImage, myX, myY);
		myHUDItems = new HashSet<HUDItem>();				
	}

	public HeadsUpDisplay(int x, int y) {
		myX = x;
		myY = y;
		myHUDItems = new HashSet<HUDItem>();
	}
	
	public void addGraphicItem(HUDItem item) {
		myHUDItems.add(item);
	}
	
	public void addTextItem(HUDItem item) {
		myHUDItems.add(item);
	}

	public HashSet<HUDItem> getHUDItems(){
		return myHUDItems;
	}
	
	public void render(Graphics2D g) {

		myHUDSprite.render(g);
		
		for (HUDItem item : myHUDItems) {

			item.render(g);

		}
	}

	public void update(long elapsedTime) {
		
		myHUDSprite.update(elapsedTime);
		
		for (HUDItem item : myHUDItems) {

			item.update(myX,myY,elapsedTime);

		}
		
	}
}

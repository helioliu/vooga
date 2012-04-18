package hudDisplay;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.HashSet;

import StateMachines.BryanStateMachine;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

public class HeadsUpDisplay {

	private int myX;
	private int myY;
	private BufferedImage myHUDImage;
	private HashSet<HUDItem> myHUDItems;
	private HashSet<GraphicItem> myHUDGraphicItems;
	private HashSet<TextItem> myHUDScoreItems;
	private SpriteGroup HUDGROUP;
	HUDEventManager myHUDEventManager;
	
	public HeadsUpDisplay(BufferedImage HUDImage, int x, int y) {
		myHUDImage = HUDImage;
		myX = x;
		myY = y;
		HUDGROUP = new SpriteGroup("hud");
		myHUDItems = new HashSet<HUDItem>();
		myHUDGraphicItems = new HashSet<GraphicItem>();
		myHUDScoreItems = new HashSet<TextItem>();
		
		createEmptyHeadsUpDisplay();
		
	}

	public HeadsUpDisplay(int x, int y) {
		myX = x;
		myY = y;
		myHUDEventManager = new HUDEvents((this));
		HUDGROUP = new SpriteGroup("hud");
		myHUDItems = new HashSet<HUDItem>();
		myHUDGraphicItems = new HashSet<GraphicItem>();
		myHUDScoreItems = new HashSet<TextItem>();
	}

	private void createEmptyHeadsUpDisplay() {
		Sprite emptyHUD = new Sprite(myHUDImage, myX, myY);
		HUDGROUP.add(emptyHUD);

	}

	public void addGraphicItem(GraphicItem item) {
		myHUDGraphicItems.add(item);
		myHUDItems.add(item);
	}
	
	public void addTextItem(TextItem item) {
		myHUDScoreItems.add(item);
		myHUDItems.add(item);
	}

	public HashSet<HUDItem> getHUDItems(){
		return myHUDItems;
	}
	public void render(Graphics2D g) {

		for (GraphicItem graphicItem : myHUDGraphicItems) {

			HUDGROUP.add(graphicItem.getSpriteVersion());

		}
		HUDGROUP.render(g);
		
		for(TextItem scoreItem : myHUDScoreItems){
			scoreItem.render(g);
		}
	}

	public void update(long elapsedTime) {
		
			HUDGROUP.update(elapsedTime);
	}
}

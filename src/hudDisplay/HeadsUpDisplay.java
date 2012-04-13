package hudDisplay;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.HashSet;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

public class HeadsUpDisplay {

	private int myX;
	private int myY;
	private BufferedImage myHUDImage;
	private HashSet<HUDItem> myHUDItems;
	private HashSet<GraphicItem> myHUDGraphicItems;
	private HashSet<ScoreItem> myHUDScoreItems;
	private SpriteGroup HUDGROUP;

	public HeadsUpDisplay(BufferedImage HUDImage, int x, int y) {
		myHUDImage = HUDImage;
		myX = x;
		myY = y;
		HUDGROUP = new SpriteGroup("hud");
		myHUDItems = new HashSet<HUDItem>();
		myHUDGraphicItems = new HashSet<GraphicItem>();
		myHUDScoreItems = new HashSet<ScoreItem>();
		createEmptyHeadsUpDisplay();
	}

	public HeadsUpDisplay(int x, int y) {
		myX = x;
		myY = y;
		HUDGROUP = new SpriteGroup("hud");
		myHUDItems = new HashSet<HUDItem>();
		myHUDGraphicItems = new HashSet<GraphicItem>();
		myHUDScoreItems = new HashSet<ScoreItem>();
	}

	private void createEmptyHeadsUpDisplay() {
		Sprite emptyHUD = new Sprite(myHUDImage, myX, myY);
		HUDGROUP.add(emptyHUD);

	}

	public void addGraphicItem(GraphicItem item) {
		myHUDGraphicItems.add(item);
		myHUDItems.add(item);
	}
	
	public void addScoreItem(ScoreItem item) {
		myHUDScoreItems.add(item);
		myHUDItems.add(item);
	}

	public void render(Graphics2D g) {

		for (GraphicItem graphicItem : myHUDGraphicItems) {

			HUDGROUP.add(graphicItem.getSpriteVersion());

		}
		HUDGROUP.render(g);
		
		for(ScoreItem scoreItem : myHUDScoreItems){
			scoreItem.render(g);
		}
	}

	public void update(long elapsedTime) {
		for (HUDItem HUDItem : myHUDItems) {
				int oldScore = HUDItem.getItemScore();
				int newScore = HUDItem.getAssociatedSprite().getScore(HUDItem.getScoreID());

				if (oldScore != newScore) {
					HUDItem.adjust(newScore);
				}
		}
			HUDGROUP.update(elapsedTime);
	}
}

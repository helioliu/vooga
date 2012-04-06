package hudDisplay;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.Sprite;

public class HUDisplayImp implements HUDInterface {

	HUDInterface hd;
	BufferedImage background;
	int height;

	HUDisplayImp(HUDInterface hd, BufferedImage background, int height) {
		this.hd = hd;
		this.background = background;
		this.height = height;

	}

	@Override
	public ArrayList<Sprite> createWidget() {
		ArrayList<Sprite> spritesToAdd = new ArrayList<Sprite>();
		spritesToAdd.add( new Sprite(background, 0, 0));
		return spritesToAdd;
		

	}

}
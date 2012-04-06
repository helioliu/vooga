package hudDisplay;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.golden.gamedev.object.Sprite;

public class BarDecorator extends HUDDecorator {

	HUDInterface hd;
	BufferedImage bar;
	int x;
	int y;

	public BarDecorator(HUDInterface hd, BufferedImage bar, int x,int y) {
		super(hd);
		this.hd = hd;
		this.bar = bar;
		this.x = x;
		this.y = y;
	}

	@Override
	public ArrayList<Sprite> createWidget() {
		ArrayList<Sprite> spritesToAdd = new ArrayList<Sprite>();
		
		spritesToAdd.add( new Sprite(bar, x, y));
		return spritesToAdd;
	}

}

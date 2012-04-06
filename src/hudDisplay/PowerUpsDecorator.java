package hudDisplay;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashSet;

import com.golden.gamedev.object.Sprite;

public class PowerUpsDecorator extends HUDDecorator {
	

	HUDInterface hd;
	HashSet<BufferedImage> powerUps;
	int x;
	int y;

	public PowerUpsDecorator(HUDInterface hd,HashSet<BufferedImage> powerUps, int x,int y) {
		super(hd);
		this.hd = hd;
		this.powerUps = powerUps;
		this.x = x;
		this.y = y;
	}

	@Override
	public ArrayList<Sprite> createWidget() {
		ArrayList<Sprite> spritesToAdd = new ArrayList<Sprite>();
		int i = 10;
		for(BufferedImage powerUp : powerUps){
		spritesToAdd.add(new Sprite(powerUp, x + i, y + 5));
		i+= 60;
	}
		
		return spritesToAdd;
	}

}

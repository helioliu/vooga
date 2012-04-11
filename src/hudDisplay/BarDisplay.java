package hudDisplay;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import com.golden.gamedev.object.Sprite;

public class BarDisplay implements HUDAttribute {

	private Sprite mySprite;
	private BufferedImage myBarImage;
	private int myX;
	private int myY;

	public BarDisplay(BufferedImage barImage, int x, int y) {
		mySprite = new Sprite(barImage, x, y);
		myX = x;
		myY = y;
		myBarImage = barImage;

	}

	public void AdjustBar(int x) {
		if(myBarImage.getWidth() - x <= 0)
			return;

			Image scaledImage = myBarImage.getScaledInstance(myBarImage.getWidth() - x, myBarImage.getHeight(), 1);
			myBarImage = convertToBufferedImage(scaledImage);

			mySprite.setActive(false);
			mySprite = new Sprite(myBarImage, myX, myY);
			mySprite.setActive(true);
	}
	
	public static BufferedImage convertToBufferedImage(Image image){ 
	    int width = image.getWidth(null); 
	    int height = image.getHeight(null); 
	    BufferedImage bufferedimage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB); 
	    
	    Graphics2D g = bufferedimage.createGraphics(); 
	    g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR); 
	    g.drawImage(image, 0, 0, width, height, null); 
	    g.dispose(); 
	    
	    return bufferedimage; 
	}

	@Override
	public Sprite getSprite() {
		return mySprite;
	}

}

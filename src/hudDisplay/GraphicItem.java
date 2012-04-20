package hudDisplay;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import com.golden.gamedev.object.Sprite;

public class GraphicItem extends HUDItem{
	
	private Sprite mySprite;
	private BufferedImage myImage;
	private int myX;
	private int myY;
	private Stat myStat;

	public GraphicItem(BufferedImage image, int x, int y, Stat s1) {
		
		myImage = image;
		mySprite = new Sprite(image, x, y);
		myX = x;
		myY = y;
	}

	@Override
	public void render(Graphics2D g) {
		mySprite.render(g);
		
	}

	@Override
	public void update(long elapsedTime) {
		Image scaledImage = myImage.getScaledInstance(myImage.getWidth(), myImage.getHeight(), 1);
		myImage = convertToBufferedImage(scaledImage);

		mySprite = new Sprite(myImage, myX, myY);
		
	}
	
	public static BufferedImage convertToBufferedImage(Image image) {
		int width = image.getWidth(null);
		int height = image.getHeight(null);
		BufferedImage bufferedimage = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);

		Graphics2D g = bufferedimage.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.drawImage(image, 0, 0, width, height, null);
		g.dispose();

		return bufferedimage;
	}
	

}

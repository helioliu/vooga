package hudDisplay;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import sprites.BryanSprite;
import sprites.TestCharacterWithStates;

import com.golden.gamedev.object.Sprite;

public class BarItem implements GraphicItem {

	private Sprite barSprite;
	private BufferedImage myBarImage;
	private int myX;
	private int myY;
	private String myID;
	private BryanSprite myAssociatedSprite;
	public int myScore;

	@Override
	public int getItemScore() {
		return myScore;
	}

	public BarItem(BufferedImage barImage, int x, int y, String name,
			BryanSprite s1) {
		barSprite = new Sprite(barImage, x, y);
		myX = x;
		myY = y;
		myBarImage = barImage;
		myAssociatedSprite = s1;
		myScore = s1.getScore(name);
		myID = name;

	}

	@Override
	public void adjust(int newScore) {

		int delta = myScore - newScore;
		if (myBarImage.getWidth() - delta <= 0) {
			return;
		}
		Image scaledImage = myBarImage.getScaledInstance(myBarImage.getWidth()
				- delta, myBarImage.getHeight(), 1);
		myBarImage = convertToBufferedImage(scaledImage);
		myScore = newScore;

		barSprite.setActive(false);
		barSprite = new Sprite(myBarImage, myX, myY);
		barSprite.setActive(true);
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

	@Override
	public Sprite getSpriteVersion() {
		return barSprite;
	}

	@Override
	public BryanSprite getAssociatedSprite() {
		return myAssociatedSprite;
	}

	@Override
	public String getScoreID() {
		return myID;
	}

}

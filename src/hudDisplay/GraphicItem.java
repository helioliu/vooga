package hudDisplay;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import sprites.GeneralSprite;
import com.golden.gamedev.object.Sprite;

public class GraphicItem extends HUDItem {

	private Sprite mySprite;
	private BufferedImage myImage;
	private int myX;
	private int myY;
	private Stat myStat;
	private boolean follow;
	private GeneralSprite spriteToFollow;

	public GraphicItem(BufferedImage image, int x, int y, Stat stat) {

		myImage = image;
		myStat = stat;
		myX = x;
		myY = y;
		follow = false;
	}

	@Override
	public void render(Graphics2D g) {
		mySprite.render(g);
	}

	@Override
	public void update(int HUDX, int HUDY, long elapsedTime) {
		int newImageWidth;
		if ((int) (myImage.getWidth() * (myStat.getValue() / myStat.getStartValue())) == 0)
			newImageWidth = 2;
		else if (myStat.getValue() / myStat.getStartValue() > .97)
			newImageWidth = 100;
		else
			newImageWidth = (int) (myImage.getWidth() * (myStat.getValue() / myStat.getStartValue()));

		Image scaledImage = myImage.getScaledInstance(newImageWidth,
				myImage.getHeight(), 1);
		myImage = convertToBufferedImage(scaledImage);
		
		if(follow)
		{
			mySprite = new Sprite(myImage, spriteToFollow.getX() -50, spriteToFollow.getY() -20);
			return;
		}
		
		mySprite = new Sprite(myImage, HUDX + myX, HUDY + myY);

	}

	private static BufferedImage convertToBufferedImage(Image image) {
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

	public void Follow(GeneralSprite s1) {
		spriteToFollow = s1;
		follow = true;
	}

}

package hudDisplay;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import sprites.GeneralSprite;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.Timer;

public class GraphicItem extends HUDItem {

	private Sprite mySprite;
	private BufferedImage myImage;
	private int myX;
	private int myY;
	private Stat myStat;
	private boolean flash;
	private double myFlashpoint;
	private Timer myFlashTimer;
	private boolean HUDactive;
	private int myOriginalWidth;

	public GraphicItem(BufferedImage image, int x, int y, Stat stat) {

		myImage = image;
		myStat = stat;
		myX = x;
		myY = y;
		HUDactive  = true;
		myFlashTimer = new Timer(50);
		myOriginalWidth = image.getWidth();
	}

	@Override
	public void render(Graphics2D g) {
		if (getMySprite().isActive() && HUDactive) {
			getMySprite().render(g);
		}
	}

	@Override
	public void update(int HUDX, int HUDY, long elapsedTime) {
		findNewSize();
		mySprite = new Sprite(myImage, HUDX + myX, HUDY + myY);
		
		if (flash && myFlashpoint >= myStat.getMyValue())
			mySprite.setActive(myFlashTimer.action(elapsedTime));

		else
			mySprite.setActive(true);

	}

	public void findNewSize() {
		int newImageWidth;
		if ((int) (getMyImage().getWidth() * (myStat.getValue() / myStat
				.getStartValue())) == 0)
			newImageWidth = 2;
		else if (myStat.getValue() / myStat.getStartValue() > .97)
			newImageWidth = myOriginalWidth;
		else
			newImageWidth = (int) (getMyImage().getWidth() * (myStat.getValue() / myStat
					.getStartValue()));

		Image scaledImage = getMyImage().getScaledInstance(newImageWidth,
				getMyImage().getHeight(), 1);
		myImage = convertToBufferedImage(scaledImage);
	}

	public void setToFlash(boolean onOff, int minimum) {
		flash = onOff;
		myFlashpoint = minimum;
		myFlashTimer.setActive(true);

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

	@Override
	public void activateItem(boolean onOff) {
		HUDactive = onOff;
	}

	public Sprite getMySprite() {
		return mySprite;
	}

	public void setMySprite(Sprite mySprite) {
		this.mySprite = mySprite;
	}

	public BufferedImage getMyImage() {
		return myImage;
	}

	public int getMyX() {
		return myX;
	}

	public int getMyY() {
		return myY;
	}


}

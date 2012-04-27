package hudDisplay;
import java.awt.image.BufferedImage;
import com.golden.gamedev.object.Sprite;
import sprites.GeneralSprite;

public class FollowGraphicItem extends GraphicItem {
	
	GeneralSprite spriteToFollow;

	public FollowGraphicItem(BufferedImage image, int x, int y, Stat stat, GeneralSprite s1) {
		super(image, (int) x, y, stat);
		spriteToFollow = s1;
	}

	@Override 
	public void update(int HUDX, int HUDY, long elapsedTime) {
			findNewSize();
			setMySprite(new Sprite(getMyImage(), spriteToFollow.getX() + getMyX(), spriteToFollow.getY() + getMyY()));
	}

}

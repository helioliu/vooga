package interactiveSprites;
import game.Platfomer;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import sprites.LevelEditable;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.CollisionGroup;

public class KoopaShell_IS extends Sprite implements LevelEditable, InteractiveSprite {
	
	Platfomer myGame;
	String path;
	String myType;
	
	public KoopaShell_IS(BufferedImage bufferedImage, int i, int j, Platfomer game) {
		super(bufferedImage, i, j);
		myType = "koopa shell";
		myGame = game;
	}
	
	public void primaryAction(CollisionGroup c) {
		if(c.getCollisionSide()== c.BOTTOM_TOP_COLLISION) {
			
		}
		if(c.getCollisionSide()== c.TOP_BOTTOM_COLLISION) {
			
		}
		if(c.getCollisionSide()== c.LEFT_RIGHT_COLLISION) {
			this.setHorizontalSpeed(-.3);
		}
		if(c.getCollisionSide()== c.RIGHT_LEFT_COLLISION) {
			this.setHorizontalSpeed(.3);
		}
	}
	
	public void userMove() {
		this.setX(myGame.CHARACTER.getActiveSprite().getX() + this.width);
		this.setY(myGame.CHARACTER.getActiveSprite().getY());
	}
	
	public String getType() {
		return myType;
	}
	
	public ArrayList<Object> writableObject() {
		ArrayList<Object> o= new ArrayList<Object>();
		o.add(path);
		o.add(getX());
		o.add(getY());
		return o;
	}
	
	public void parse(ArrayList<Object> o, Platfomer game) {
		myGame=game;
		path= (String) o.get(0);
		setX((Integer) o.get(1));
		setY((Integer) o.get(2));
		myGame.INTERACTIVE_SPRITES.add(this);
		
	}

	@Override
	public void setInitX(double d) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setInitY(double val) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setInitPath(String path) {
		// TODO Auto-generated method stub
		
	}
	
	

}
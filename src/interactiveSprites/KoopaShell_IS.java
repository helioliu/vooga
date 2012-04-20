package interactiveSprites;
import game.Platformer;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import sprites.Bad_Guys;
import sprites.GeneralSprite;
import sprites.LevelEditable;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.CollisionGroup;

public class KoopaShell_IS extends GeneralSprite implements LevelEditable, InteractiveSprite {
	
	Platformer myGame;
	String path;
	String myType;
	
	public KoopaShell_IS(BufferedImage bufferedImage, int i, int j, Platformer game) {
		super(bufferedImage, i, j);
		myType = "koopa shell";
		myGame = game;
	}
	
	public KoopaShell_IS(){
		
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
	
	public ArrayList<String> writableObject() {
		ArrayList<String> list= new ArrayList<String>();
		list.add(this.getClass().toString());
		list.add(path);
		list.add(getX() +"");
		list.add(getY() +"");
		return list;
	}
	
	public Sprite parse(ArrayList<String> o, Platformer game) {
		KoopaShell_IS k= new KoopaShell_IS();
			k.path=o.get(1);
			k.setX( Double.parseDouble(o.get(2)));
			k.setY( Double.parseDouble(o.get(3)));
			File file= new File(path);
			BufferedImage image;
			try {
				image = ImageIO.read(file);
				k.setImage(image);		} 
			catch (IOException e) {
				e.printStackTrace();
			}
			return k;
		
	}


		@Override
	public Boolean isInstanceOf(ArrayList<String> o) {
			if (this.getClass().toString().equals(o.get(0))) {
				return true;
			}
			return false;
	}

	

}
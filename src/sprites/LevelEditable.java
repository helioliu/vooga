package sprites;

import game.Platformer;

import java.util.ArrayList;

import com.golden.gamedev.object.Sprite;

public interface LevelEditable {
	
	public ArrayList<String> writableObject() ;

	public Sprite parse(ArrayList<String> o, Platformer myGame) ;
	
	public Boolean isInstanceOf(ArrayList<String> o);
	

}

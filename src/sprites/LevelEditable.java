package sprites;

import game.Platfomer;

import java.util.ArrayList;

public interface LevelEditable {
	public ArrayList<Object> writableObject() ;

	public void parse(ArrayList<Object> o, Platfomer myGame) ;
	
	public void setInitX(double d);
	
	public void setInitY(double val);
	
	public void setInitPath(String path);

}

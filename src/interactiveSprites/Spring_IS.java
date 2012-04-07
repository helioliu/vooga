package interactiveSprites;

import game.Platfomer;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import sprites.LevelEditable;

import com.golden.gamedev.GameObject;
import com.golden.gamedev.object.Sprite;

public abstract class Spring_IS extends Sprite implements LevelEditable {
	
	Platfomer myGame;
	String path;
	
	
	public Spring_IS(GameObject owner) {
		super();
		//super(owner.getImage("MarioSpring.png", false), 0, 0);
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
		myGame.SPRINGS.add(this);
	}
	
	

}

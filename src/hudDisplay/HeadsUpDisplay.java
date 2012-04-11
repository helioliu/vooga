package hudDisplay;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import sprites.TestCharacterWithStates;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

import core.EventListener;

public class HeadsUpDisplay {
	
	private int myX;
	private int myY;
	private BufferedImage myHUDImage;
	private Map<String, HUDAttribute> myAttributes;
	private SpriteGroup HUDGROUP;
	private TestCharacterWithStates mySprite;
	
	public HeadsUpDisplay(BufferedImage HUDImage, int x, int y, TestCharacterWithStates sprite){
		myHUDImage = HUDImage;
		myX = x;
		myY = y;
		mySprite = sprite;
		HUDGROUP = new SpriteGroup("hud");
		setMyAttributes(new HashMap<String, HUDAttribute>());
		createEmptyHeadsUpDisplay();
		mySprite.setHUD(this);
	}
	
	public HeadsUpDisplay(int x, int y, TestCharacterWithStates sprite){
		myX = x;
		myY = y;
		mySprite = sprite;
		HUDGROUP = new SpriteGroup("hud");
		setMyAttributes(new HashMap<String, HUDAttribute>());
		mySprite.setHUD(this);
	}
	
	private void createEmptyHeadsUpDisplay(){
		Sprite emptyHUD = new Sprite(myHUDImage, myX, myY);
		HUDGROUP.add(emptyHUD);
		
	}
	
	public void add(HUDAttribute attribute, String name){
		getMyAttributes().put(name, attribute);
		
	}
	
	public SpriteGroup getSpriteGroup(){
		return HUDGROUP;
	}
	
	

	public void render(Graphics2D g) {
		
		for(String temp : getMyAttributes().keySet()){
			
			HUDGROUP.add(getMyAttributes().get(temp).getSprite());
			
		}
		HUDGROUP.render(g);	
	}

	public void update(long elapsedTime) {
		HUDGROUP.update(elapsedTime);
	}

	public Map<String, HUDAttribute> getMyAttributes() {
		return myAttributes;
	}

	public void setMyAttributes(Map<String, HUDAttribute> myAttributes) {
		this.myAttributes = myAttributes;
	}

}

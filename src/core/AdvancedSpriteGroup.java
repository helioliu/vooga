package core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

public class AdvancedSpriteGroup<T extends Sprite> extends SpriteGroup{

	private ArrayList<T> mySprites;
	
	public AdvancedSpriteGroup(String groupName, T ... sprites){
		super(groupName);
		mySprites = new ArrayList<T>();
		this.addSprites(sprites);
	}
	
	public List<T> getSprites(){
		return mySprites;
	}

	public void addSprites(T ... sprites){
		mySprites.addAll(Arrays.asList(sprites));
	}
	
}

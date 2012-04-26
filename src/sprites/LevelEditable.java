package sprites;

import game.Platformer;

import java.util.ArrayList;

import org.jdom2.Element;

import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.Sprite;

public interface LevelEditable {
	
	public Element writeElement() ;

	public Sprite parse(Element e);	

}

package sprites;

import org.jdom2.Element;

import com.golden.gamedev.object.Sprite;

public interface LevelEditable {
	
	public Element writeElement() ;

	public Sprite parse(Element sprite);	

}

package sprites;

import hudDisplay.Stat;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.jdom2.Element;



import collisions.Hitbox;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.sprite.AdvanceSprite;

public abstract class GeneralSprite extends AdvanceSprite implements Boxable, LevelEditable {

	private Map<String, Stat> myStats = new HashMap<String,Stat>();	
	private List<Hitbox> myHitboxes;
	private double myGravityValue; 
	private String path;
	private String group;
	
	
	public GeneralSprite(BufferedImage i) {
		super();
		BufferedImage[] image = new BufferedImage[1];
		image[0] = i;
		setImages(image);
	}
	
	public GeneralSprite(BufferedImage[] i) {
		super(i);
	}
	
	public GeneralSprite(BufferedImage i, double x, double y) {
		super(x,y);
		setImage(i);
	}
	
	public GeneralSprite(BufferedImage[] i, double x, double y) {
		super(i,x,y);
	}
	
	public GeneralSprite(double x, double y) {
		super(x,y);
	}
	public GeneralSprite()
	{
		super();
	}
	public void createStat(String name, Stat stat) {
		getMyStats().put(name, stat);
	}
	
	public Stat getStat(String name){
		return getMyStats().get(name);
	}

	public Map<String, Stat> getMyStats() {
		return myStats;
	}

	public void setMyStats(Map<String, Stat> myStats) {
		this.myStats = myStats;
	}
	
	public void changeStat(String name, double x){	
		getStat(name).adjust(x);
	
	}

	public List<Hitbox> getHitboxes() {
		return myHitboxes;
	}

	public void setGravity(double d)
	{
		myGravityValue = d;
	}
	public double getGravity()
	{
		return myGravityValue;
	}
	
	public void setImage(BufferedImage i) {
		BufferedImage[] image = new BufferedImage[1];
		image[0] = i;
		setImages(image);
	}


	public void setInitPath(String path) {
		this.path=path;
		
	}
	
	public abstract String getClassName();
	
	public Element writeElement() {
		Element sprite= new Element("sprite");
		sprite.addContent(new Element("class").addContent(getClassName()));
		sprite.addContent(new Element("image").addContent(path));
		sprite.addContent(new Element("group").addContent(group));
		sprite.addContent(new Element("x").addContent(getX() + ""));
		sprite.addContent(new Element("y").addContent(getY()+ ""));		
		for(String s: myStats.keySet()) {
			sprite.addContent(new Element(s).addContent(myStats.get(s).toString()));
		}
		return sprite;
	}

	public Sprite parse(Element e){
		path=e.getChild("image").getText();
		File file=new File(path);
		BufferedImage image=null;
		try {
			image = ImageIO.read(file);
		} catch (IOException e1) {
			System.out.print("IOException");
		}
		int x = Integer.parseInt(e.getChildText("x"));
		int y = Integer.parseInt(e.getChildText("y"));
		Sprite s = getThisTypeOfSprite();
		s.setImage(image);
		s.setX(x);
		s.setY(y);
		return s;
		
	}
	
	public abstract Sprite getThisTypeOfSprite();

}

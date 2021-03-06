package sprites;

import game.Platformer;
import hudDisplay.Stat;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.jdom2.Element;



import collisions.Hitbox;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.sprite.AdvanceSprite;

public class GeneralSprite extends AdvanceSprite implements Boxable, LevelEditable {

	private Map<String, Stat> myStats = new HashMap<String,Stat>();	
	protected List<Hitbox> myHitboxes;
	private double myGravityValue; 
	private String path;
	
	
	public GeneralSprite(BufferedImage i) {
		super();
		setImage(i);
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


	public void setPath(String path) {
		this.path=path;
		
	}
	
	public String getClassName() {
		return this.getClass().toString();
	}
	
	public Element writeElement() {
		Element sprite= new Element("sprite");
		sprite.addContent(new Element("class").addContent(this.getClass().toString()));
		sprite.addContent(new Element("image").addContent(path));
		sprite.addContent(new Element("x").addContent(getX() + ""));
		sprite.addContent(new Element("y").addContent(getY()+ ""));		
		if (this.myStats==null ||this.myStats.isEmpty()) {
			return sprite;
		} else {
			for (String key: myStats.keySet()) {
				Element e= new Element(key).addContent(myStats.get(key).toString());
				sprite.addContent(e);
			}
			return sprite;
		}
		
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
		setX(Double.parseDouble(e.getChildText("x")));
		setY(Double.parseDouble(e.getChildText("y")));
		setImage(image);
		return this;
	}


}

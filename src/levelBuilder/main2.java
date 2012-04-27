package levelBuilder;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import sprites.*;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import sprites.LevelEditable;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.background.ImageBackground;

public class main2 {
	public static void main(String[] args){
		SAXBuilder builder = new SAXBuilder();
        File xmlFile = new File("level1.xml");
				 
		Document document;
		try {
			document = (Document) builder.build(xmlFile);

		
		Element root = document.getRootElement();

//		Element backgroundchild = root.getChild("background");
//		String backgroundimagepath=backgroundchild.getText();
//		
//		//creating background image
//		BufferedImage myBackground=null;
//	    try {
//		File backgroundPathFile= new File(backgroundimagepath);
//
//	    myBackground = ImageIO.read(backgroundPathFile);
//		} catch (IOException e1) {
//			System.out.print("no background");
//		}
//		playfield.setBackground(new ImageBackground(myBackground));

		//
		Element sprites= root.getChild("sprites");
		List allChildren = sprites.getChildren();
		for (int i=0;i<allChildren.size();i++) {
			Element sprite = (Element) allChildren.get(i);
			String classname=sprite.getChild("class").getText();
			classname=classname.split(" ")[1];
			System.out.println(classname);
			LevelEditable LE= (LevelEditable) Class.forName(classname).newInstance();
			Sprite s = LE.parse(sprite);
			System.out.println(s.toString());

		}

		} catch (JDOMException e) {
			System.out.print("jdomexception");
		} catch (IOException e) {
			System.out.print("IOException");
		} catch (InstantiationException e) {
			System.out.print("InstantiationException");
		} catch (IllegalAccessException e) {
			System.out.print("IllegalAccessException");
		} catch (ClassNotFoundException e) {
			System.out.print("ClassNotFoundException");
		}
	

	}
}

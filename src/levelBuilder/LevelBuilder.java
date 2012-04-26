package levelBuilder;

import game.Platformer;
import game.PlatformGame;

import interactiveSprites.InteractiveSpriteCollision;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.imageio.ImageIO;

import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.contrib.beans.JDOMBean;
import org.jdom2.input.SAXBuilder;
import org.jdom2.input.sax.SAXBuilderEngine;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import sprites.*;
import sprites.Character;

import levelEditor.GameFile;
import levelEditor.SpriteInfo;

import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.background.ImageBackground;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class LevelBuilder {
	private String xmlpath;
	private PlayField playfield;
	public Background background;


	public LevelBuilder(PlayField p, String xmlpath) {
		playfield=p;
		this.xmlpath=xmlpath;
	}
	
	public void createLevel() {
		SAXBuilder builder = new SAXBuilder();
	    File xmlFile = new File(xmlpath);
		
		
			 
			Document document;
			try {
				document = (Document) builder.build(xmlFile);

			
			Element root = (Element) document.getRootElement();

			Element backgroundchild = (Element) root.getFirstChild();
			String backgroundimagepath=backgroundchild.getTextContent();
			
			//creating background image
			BufferedImage myBackground=null;
		    try {
			File backgroundPathFile= new File(backgroundimagepath);

		    myBackground = ImageIO.read(backgroundPathFile);
			} catch (IOException e1) {
				System.out.print("no background");
			}
			playfield.setBackground(new ImageBackground(myBackground));

			//
			Element spriteschild=(Element) root.getNextSibling();
			NodeList allChildren = spriteschild.getChildNodes();
			for (int i=0;i<allChildren.getLength();i++) {
				Element sprite =(Element) allChildren.item(i);
				String classname=sprite.getAttribute("class");
				LevelEditable LE= (LevelEditable) Class.forName(classname).newInstance();

				String groupname=sprite.getAttribute("group");
				playfield.getGroup(groupname).add((Sprite)LE);
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


	


		


package levelBuilder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import sprites.*;
import sprites.Character;

public class main {
	public static void main(String[] args) throws IOException {
		
		Document doc = new Document();
		Element root=new Element("root").setText("This is the root");
		doc.addContent(root);
		Element background= new Element("background").setText("/home/rjk13/workspace/vooga/src/images/background.jpg");
		root.addContent(background);

	       GeneralSprite s1 = new Chris_TestSprite();
	        //BufferedImage[] images = new BufferedImage[1];
	        //	images[0] = ;
	        s1.setImage(ImageIO.read(new File("src/images/mario1.png")));
	        s1.setLocation(300, 200);
	        s1.setPath("src/images/mario1.png");
	  

			GeneralSprite flag = new Flag(getImage("images/finalflag.png"), 1750, 106);
			SpriteGroup fg = new SpriteGroup("flag");

			GeneralSprite castle = new GeneralSprite(getImage("images/castle.gif"), 1800, 300);
			
			mainChar = new MainCharacter();
			mainChar.setImages(getImages("images/mariocharpng.png",3,2));
			mainChar.setLocation(200, 100);
			mainChar.setAnimate(false);
			
			GeneralSprite enemy1 = new HomingEnemy(mainChar);
	        enemy1.setImage(getImage("images/boo.jpg"));
	        enemy1.setLocation(300, 300);
	        
	        
	        GeneralSprite enemy2 = new HomingEnemy(mainChar);
	        enemy2.setImage(getImage("images/boo.jpg"));
	        enemy2.setLocation(700, 300);
	        
	        GeneralSprite enemy3 = new HomingEnemy(mainChar);
	        enemy3.setImage(getImage("images/boo.jpg"));
	        enemy3.setLocation(1200, 300);
	        
	        GeneralSprite enemy4 = new HomingEnemy(mainChar);
	        enemy4.setImage(getImage("images/boo.jpg"));
	        enemy4.setLocation(1700, 300); 


	        GeneralSprite blocker1 = new GeneralSprite(ImageIO.read(new File("src/images/block.png")));
	        GeneralSprite blocker2 = new GeneralSprite(ImageIO.read(new File("src/images/block.png")));
	        GeneralSprite blocker3 = new GeneralSprite(ImageIO.read(new File("src/images/block.png")));
	        GeneralSprite blocker4 = new GeneralSprite(ImageIO.read(new File("src/images/block.png")));

	        blocker1.setLocation(200, 200);
	        blocker2.setLocation(500,200);
	        blocker3.setLocation(490,100);
	        blocker4.setLocation(210,100);
	        //
	        blocker1.setPath("src/images/block.png");
	        blocker2.setPath("src/images/block.png");
	        blocker3.setPath("src/images/block.png");
	        blocker4.setPath("src/images/block.png");
	        
	        blocker1.setGroup("blockers");
	        blocker2.setGroup("blockers");
	        blocker3.setGroup("blockers");
	        blocker4.setGroup("blockers");
	        GeneralSprite wall1 = new GeneralSprite(ImageIO.read(new File("src/images/bricks1.png")));
	        wall1.setLocation(275,450);
	        wall1.setPath("src/images/bricks1.png");
	        GeneralSprite wall2 = new GeneralSprite(ImageIO.read(new File("src/images/bricks1.png")));
	        wall2.setLocation(225,450);
	        wall2.setPath("src/images/bricks1.png");
	        GeneralSprite wall3 = new GeneralSprite(ImageIO.read(new File("src/images/bricks1.png")));
	        wall3.setLocation(250,450);
	        wall3.setPath("src/images/bricks1.png");
	        GeneralSprite wall4 = new GeneralSprite(ImageIO.read(new File("src/images/bricks1.png")));
	        wall4.setLocation(300,450);
	        wall4.setPath("src/images/bricks1.png");
	        GeneralSprite wall5 = new GeneralSprite(ImageIO.read(new File("src/images/bricks1.png")));
	        wall5.setLocation(325,450);
	        wall5.setPath("src/images/bricks1.png");
	        GeneralSprite wall6 = new GeneralSprite(ImageIO.read(new File("src/images/bricks1.png")));
	        wall6.setLocation(350,450);
	        wall6.setPath("src/images/bricks1.png");
	        GeneralSprite wall7 = new GeneralSprite(ImageIO.read(new File("src/images/bricks1.png")));
	        wall7.setLocation(375,450);
	        wall7.setPath("src/images/bricks1.png");
	        GeneralSprite wall8 = new GeneralSprite(ImageIO.read(new File("src/images/bricks1.png")));
	        wall8.setLocation(400,450);
	        wall8.setPath("src/images/bricks1.png");
	        GeneralSprite wall9 = new GeneralSprite(ImageIO.read(new File("src/images/bricks1.png")));
	        wall9.setLocation(50,450);
	        wall9.setPath("src/images/bricks1.png");
	        GeneralSprite wall10 = new GeneralSprite(ImageIO.read(new File("src/images/bricks1.png")));
	        wall10.setLocation(75,450);
	        wall10.setPath("src/images/bricks1.png");
	        GeneralSprite wall11 = new GeneralSprite(ImageIO.read(new File("src/images/bricks1.png")));
	        wall11.setLocation(100,450);
	        wall11.setPath("src/images/bricks1.png");
	        GeneralSprite wall12 = new GeneralSprite(ImageIO.read(new File("src/images/bricks1.png")));
	        wall12.setLocation(350,450);
	        wall12.setPath("src/images/bricks1.png");
	        GeneralSprite wall13 = new GeneralSprite(ImageIO.read(new File("src/images/bricks1.png")));
	        wall13.setLocation(425,450);
	        wall13.setPath("src/images/bricks1.png");
	        GeneralSprite wall14 = new GeneralSprite(ImageIO.read(new File("src/images/bricks1.png")));
	        wall14.setLocation(500,300);
	        wall14.setPath("src/images/bricks1.png");
	        GeneralSprite wall15 = new GeneralSprite(ImageIO.read(new File("src/images/bricks1.png")));
	        wall15.setLocation(525,300);
	        wall15.setPath("src/images/bricks1.png");
	        GeneralSprite wall16 = new GeneralSprite(ImageIO.read(new File("src/images/bricks1.png")));
	        wall16.setLocation(550,300);
	        wall16.setPath("src/images/bricks1.png");
	  
	        wall1.setGroup("walls");
	        wall2.setGroup("walls");
	        wall3.setGroup("walls");
	        wall4.setGroup("walls");
	        wall5.setGroup("walls");
	        wall6.setGroup("walls");
	        wall7.setGroup("walls");
	        wall8.setGroup("walls");
	        wall9.setGroup("walls");
	        wall10.setGroup("walls");
	        wall11.setGroup("walls");
	        wall12.setGroup("walls");
	        wall13.setGroup("walls");
	        wall14.setGroup("walls");
	        wall15.setGroup("walls");
	        wall16.setGroup("walls");
	        

		
		Element sprites = new Element("sprites");
		root.addContent(sprites);


	    ArrayList<LevelEditable> spritelist= new ArrayList<LevelEditable>();
	    spritelist.add(s1);
	    spritelist.add(wall16);
	    spritelist.add(wall15);
	    spritelist.add(wall14);
	    spritelist.add(wall13);
	    spritelist.add(wall12);
	    spritelist.add(wall11);
	    spritelist.add(wall10);
	    spritelist.add(wall9);
	    spritelist.add(wall8);
	    spritelist.add(wall7);
	    spritelist.add(wall6);
	    spritelist.add(wall5);
	    spritelist.add(wall4);
	    spritelist.add(wall3);
	    spritelist.add(wall2);
	    spritelist.add(wall1);

	    for (int i=0;i<spritelist.size();i++){
	      Element sprite = spritelist.get(i).writeElement();
	      sprites.addContent(sprite);
	    }
	    // serialize it onto System.out
	    try {
	      XMLOutputter outputter = new XMLOutputter();
	      Format oFormat = Format.getPrettyFormat();
	      outputter.setFormat(oFormat);
	      FileOutputStream FileOS = new  FileOutputStream("level1.xml");
	      OutputStreamWriter OSWriter = new  OutputStreamWriter(FileOS);
	      outputter.output(doc, OSWriter);
	      OSWriter.close();
	      FileOS.close();

	    }
	    catch (IOException e) {
	      System.err.println(e);
	    }
	
	}

}

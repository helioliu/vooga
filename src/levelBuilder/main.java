package levelBuilder;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import sprites.*;
import sprites.Character;

public class main {
	public static void main(String[] args) {
		
		Document doc = new Document();
		Element root=new Element("root").setText("This is the root");
		doc.addContent(root);
		Element background= new Element("background").setText("/home/rjk13/workspace/vooga/src/images/background.jpg");
		root.addContent(background);
		Character character= new Character();
		character.setX(50);
		character.setY(25);
		character.setPath("/home/rjk13/workspace/vooga/src/images/mario1.png");
		character.setGroup("character");
		
		
		Element sprites = new Element("sprites");
		root.addContent(sprites);


	    ArrayList<LevelEditable> spritelist= new ArrayList<LevelEditable>();
	    spritelist.add(character);

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

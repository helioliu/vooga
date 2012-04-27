package SpriteAction;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import States.BigMario;

import sprites.GeneralSprite;
import core.EventManager;

public class ChangeImage extends SpriteAction{
	public ChangeImage(GeneralSprite s) {
		super(s);
		
	}

	public void actionPerformed(Object event) 
	{
		try {
			mySprite.setImage(ImageIO.read(new File("src/images/BigMario.jpg")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}

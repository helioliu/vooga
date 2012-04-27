package SpriteAction;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


import sprites.GeneralSprite;
import sprites.StateSprite;
import core.EventManager;

public class ChangeImage extends SpriteAction{
	private BufferedImage myImage;
	public ChangeImage(StateSprite s, BufferedImage bi) {
		super(s);
		myImage = bi;		
	}

	public void actionPerformed(Object event) 
	{
		mySprite.setImage(myImage);
	}

}

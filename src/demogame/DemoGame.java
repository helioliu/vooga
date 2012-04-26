package demogame;

import input.InputManager;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import platforms.Platform;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.background.ColorBackground;

public class DemoGame extends Game {
	private String levelFileName;
	private PlayField myPlayField;

	public DemoGame (String levelFileName) {
		this.levelFileName = levelFileName;
	}
	
    protected void initEngine() {
		super.initEngine();
		this.bsInput = new InputManager(this.bsGraphics.getComponent());
	}
	
	public void initResources() {
		myPlayField = new PlayField();
		Background b = new ColorBackground(Color.LIGHT_GRAY, 2000, 480);
		myPlayField.setBackground(b);
		
		SpriteGroup platforms = makePlatforms();
		myPlayField.addGroup(platforms);
		
	}
	
	private SpriteGroup makePlatforms() {
		SpriteGroup s = new SpriteGroup("Platforms");
		BufferedImage image = getImage("images/bricks1.png");
		for(int i=0; i < 1980; i+=32) {
			s.add(new Platform(image,i,400));
		}
		
		return s;
		
	}

	public void render(Graphics2D g) {
		myPlayField.render(g);
	}

	public void update(long timeElapsed) {
		
	}

}

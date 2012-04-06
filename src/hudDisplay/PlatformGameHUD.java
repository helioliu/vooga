package hudDisplay;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.GameFont;
import com.golden.gamedev.object.Timer;

public class PlatformGameHUD {

	BufferedImage	box;
	BufferedImage red;
	int height;
	boolean almostDead;
	Timer flashTimer;
	boolean flash;
	BufferedImage hb;
	int hblength;
	int score;
	private GameFont scoreFont;


	public PlatformGameHUD(BufferedImage box, BufferedImage red, BufferedImage hb, GameFont scoreFont) {
		
		this.box = box;
		this.red = red;
		flashTimer = new Timer(600);
		almostDead = true;
		flash = false;
		this.hb = hb;
		hblength = 300;
		this.scoreFont = scoreFont;
		height = 100;
	
	}
	
	
	public void update(long elapsedTime) {
		if(almostDead){
			flashTimer.action(elapsedTime);
		}
		
		if(flashTimer.action(elapsedTime)){
			flash = !flash;
		}
		else{
			flash = false;
		}
	


	}


	public void render(Graphics2D g) {
		if(!flash){
		g.drawImage(box, 0, 0,Background.getDefaultBackground().getWidth(),height, null);
		g.drawImage(hb, 50, 0, hblength, 40,null);
			}
		else
		g.drawImage(red,0,0,Background.getDefaultBackground().getWidth(),height, null);
		scoreFont.drawString(g, String.valueOf(score), 600, 20);
	}
	
	
	


}
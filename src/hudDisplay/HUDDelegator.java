package hudDisplay;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.GameFont;
import com.golden.gamedev.object.Timer;

public class HUDDelegator{

		BufferedImage	box;
		BufferedImage red;
		int height;
		boolean almostDead;
		Timer flashTimer;
		boolean flash;
		BufferedImage hb;
		int hblength;
		int score;
		GameFont scoreFont;
		HUDInterface hd;


		public HUDDelegator (int score, BufferedImage box, BufferedImage red, BufferedImage hb, GameFont scoreFont){ 		
			this.box = box;
			this.red = red;
			this.hb = hb;
			this.scoreFont = scoreFont;
			this.score = score;
			
			height = 100;
			flashTimer = new Timer(600);
			almostDead = true;
			flash = false;
			hblength = 300;
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
			hd = new HUDDisplay(hd, box, 0, 0, Background.getDefaultBackground().getWidth(), 40,null, g);
			hd.addToHud();
			if(flash)
			hd = new HUDDisplay(hd, red, 0, 0,Background.getDefaultBackground().getWidth(),height, null, g);
			hd.addToHud();
			
			hd = new HUDDisplay(hd, box, 0, 0,Background.getDefaultBackground().getWidth(),height, null, g);
			hd.addToHud();
			hd = new HealthDecorator(hd,hb,100,200,300,40,g);
			hd.addToHud();
			
			hd = new ScoreDecorator(hd , scoreFont, score, g);
			hd.addToHud();
		}
		
		public void changeScore(int delta){
			score += delta;
		}
		
		public void changeHealthbar(int delta) {
			if(hblength > 0)
			hblength += delta;
		}

	}



package hudDisplay;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashSet;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.GameFont;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.Timer;

public class HUDisplay {

		BufferedImage	box;
		int height = 30;
		
		boolean almostDead;
		Timer flashTimer;
		boolean flash = false;
		
		int hblength = 500;

		int score;

		HUDInterface hd;
		SpriteGroup HUD = new SpriteGroup("hud");


		public HUDisplay (BufferedImage box){ 
			flashTimer = new Timer(600);
			almostDead = true;
			MakeEmptyHUD(box,height);

		}

		public void render(Graphics2D g) {
			HUD.render(g);
			
		}
		
		public void update(long elapsedTime) {

			
			HUD.update(elapsedTime);
		


		}

		
		
		
		private void MakeEmptyHUD(BufferedImage box, int height) {
				hd = new HUDisplayImp(hd,box, height);
				ArrayList<Sprite> spritesToAdd = hd.createWidget();
				for( Sprite sprite : spritesToAdd){
					HUD.add(sprite);
				}

		}

		public void addBar(BufferedImage bar, int x, int y){
			
			hd = new BarDecorator(hd,bar,x,y);
			
			ArrayList<Sprite> spritesToAdd = hd.createWidget();
			for( Sprite sprite : spritesToAdd){
				HUD.add(sprite);
			}
			
		}
		
		
		public void addPowerUps(BufferedImage bar, HashSet<BufferedImage> icons, int x, int y){
			
			hd = new BarDecorator(hd,bar,x,y);
			
			ArrayList<Sprite> spritesToAdd = hd.createWidget();
			for( Sprite sprite : spritesToAdd){
				HUD.add(sprite);
			}
			
			hd = new PowerUpsDecorator(hd,icons,x,y);
			
			ArrayList<Sprite> spritesToAdd2 = hd.createWidget();
			for( Sprite sprite : spritesToAdd2){
				HUD.add(sprite);
			}
			
		}
		
		public void addScoreBar(GameFont scoreFont, int score, int x, int y){
			
			hd = new ScoreDecorator(hd,scoreFont,score,x ,y, null);
			
			ArrayList<Sprite> spritesToAdd = hd.createWidget();
			for( Sprite sprite : spritesToAdd){
				HUD.add(sprite);
			}
			
		}

		
//		public void changeScore(int delta){
//			score += delta;
//		}
//		
//		public void changebar(int delta) {
//			if(hblength > 0)
//			hblength += delta;
//		}

	}



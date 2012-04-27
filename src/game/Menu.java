
package game;
import input.InputManager;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Scanner;

import com.golden.gamedev.GameObject;
import com.golden.gamedev.object.GameFont;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


public class Menu extends GameObject {


		// TODO Auto-generated constructor stub


	GameFont		font;

	int				option;
    private PlatformGame  game;
    
  	public Menu(PlatformGame parent) {
		super(parent);
	    game =parent;
  	}
	@Override
	public void initResources() {
		font = fontManager.getFont(getImages("font.png", 16, 6));
		
		Gson gson = new Gson();
		Scanner Levelscanner;
	    try {
			Levelscanner = new Scanner(new File("Levels.json"));


		String LevelFile = Levelscanner.useDelimiter("\\A").next();

		Type collectionType = new TypeToken<ArrayList<String>>() {
		}.getType();
		PlatformGame.LEVEL_FILES= gson.fromJson(LevelFile, collectionType);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PlatformGame.currentLevel=0;
		System.out.println(PlatformGame.LEVEL_FILES);
	}

	
	@Override
	public void render(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		String lvl=" ";

		String sound = (bsSound.isActive()) ? "ON" : "OFF";
		if (PlatformGame.LEVEL_FILES!=null && !PlatformGame.LEVEL_FILES.isEmpty()) {
		   lvl = PlatformGame.LEVEL_FILES.get(PlatformGame.currentLevel);
		} 
		font.drawString(g, "2D PLATFORMER", 	GameFont.CENTER, 0, 150, getWidth());
		font.drawString(g, "LEVEL: "+lvl,	GameFont.CENTER, 0, 200, getWidth());
		font.drawString(g, "PRESS ENTER TO LOAD", 		GameFont.CENTER, 0, 250, getWidth());
		font.drawString(g, "PRESS ESC TO QUIT", 	GameFont.CENTER, 0, 300, getWidth());
		font.drawString(g, "PRESS E TO LAUNCH EDITOR" , 	GameFont.CENTER, 0, 350, getWidth());

		
	}

	public void update(long arg0) {
 
		if (keyPressed(KeyEvent.VK_R)) {
			System.out.println("this is r");
		}
		if (keyPressed(KeyEvent.VK_W)) {

			if (++PlatformGame.currentLevel >= PlatformGame.LEVEL_FILES.size()) {
				PlatformGame.currentLevel= 0;
			}
		}
		if (keyPressed(KeyEvent.VK_RIGHT)) {
			if (--PlatformGame.currentLevel < 0) {
				PlatformGame.currentLevel= PlatformGame.LEVEL_FILES.size()-1;
			}
		}
		if (keyPressed(KeyEvent.VK_ENTER)) {
			parent.nextGameID = PlatformGame.PLATFORMER;
			finish();

			}
		if (keyPressed(KeyEvent.VK_ESCAPE)) {
			finish();

		}
   }
		// TODO Auto-generated method stub
		
	
	
}



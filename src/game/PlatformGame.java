package game;



import java.util.ArrayList;


import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameObject;

//WARLOCK GAME
public class PlatformGame extends GameEngine {

	public static final int MENU = 0;
	public static final int PLATFORMER = 1;

	public static ArrayList<String> LEVEL_FILES;
	public static int currentLevel;
	

	@Override
	public void initResources() {
		// preload all images
		getImages("font.png", 16, 6);

	}


	@Override
	public GameObject getGame(int GameID) {
		switch (GameID) {
			case MENU 		: return new Menu(this);
			case PLATFORMER 	: return new Platfomer(this);
		}

		return null;
	}

}
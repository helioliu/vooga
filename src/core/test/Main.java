package core.test;
	import java.awt.Dimension;
import java.io.IOException;

import HUD.test.Test_game;

import com.golden.gamedev.GameLoader;

public class Main {
	public static void main(String[] args) throws IOException {
		
		GameLoader game = new GameLoader();
		game.setup(new Dick_TestGame("level1.xml"), new Dimension(640, 480), false);
		game.start();

	}
}

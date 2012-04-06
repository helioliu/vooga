
import java.awt.Dimension;

import com.golden.gamedev.GameLoader;

import core.test.PlatformGame;


public class Main {
	
		public static void main(String[] args) {
			GameLoader game = new GameLoader();
			game.setup(new PlatformGame(), new Dimension(800,600), false);
			game.start();
		}
}

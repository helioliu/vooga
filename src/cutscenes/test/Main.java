package cutscenes.test;

import java.awt.Dimension;

import com.golden.gamedev.GameLoader;

public class Main {
	public static void main (String[] args) {
		GameLoader game = new GameLoader();
		game.setup(new TestGame(), new Dimension(640, 480), false);
		game.start();
	}
}

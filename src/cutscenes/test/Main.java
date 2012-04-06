package cutscenes.test;

import java.awt.Dimension;

import com.golden.gamedev.GameLoader;

public class Main {
	public static void main (String[] args) {
		GameLoader game = new GameLoader();
		game.setup(new TestGame(), new Dimension(1,1), false);
		game.start();
		
	}
}

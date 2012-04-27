package core.test;
	import java.awt.Dimension;
import java.io.IOException;

import HUD.test.Test_game;

import com.golden.gamedev.GameLoader;

public class Main {
	public static void main(String[] args) throws IOException {
		GameLoader game = new GameLoader();
<<<<<<< HEAD
		game.setup(new Chris_TestGame(), new Dimension(640, 480), false);
=======
		game.setup(new Test_game(), new Dimension(640, 480), false);
>>>>>>> e4fc751540825e6dc2740f8b1d2b76c9988a9164
		game.start();
	}
}

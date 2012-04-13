package core.test;
	import java.awt.Dimension;
import java.io.IOException;

import com.golden.gamedev.GameLoader;

public class Main {
	public static void main(String[] args) throws IOException {
		
		GameLoader game = new GameLoader();
		game.setup(new Chris_TestGame(), new Dimension(640, 480), false);
		game.start();

	}
}

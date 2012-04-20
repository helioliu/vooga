package HUD.test;

import java.awt.Dimension;
import java.io.IOException;

import com.golden.gamedev.GameLoader;

import core.test.Chris_TestGame;

public class Main {
public static void main(String[] args) throws IOException {
		
		GameLoader game = new GameLoader();
		game.setup(new Test_game(), new Dimension(640, 480), false);
		game.start();

	}

}

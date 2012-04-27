package sampleGame;
import java.awt.Dimension;
import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameLoader;
import com.golden.gamedev.GameObject;

public class Main{


	public static void main(String[] args){
	GameLoader game = new GameLoader();
	game.setup(new DemoGame(), new Dimension(640,480), false);
	game.start();
	}

}

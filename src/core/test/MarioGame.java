//package core.test;
//
//import game.PlatformGame;
//
//import java.awt.Dimension;
//import java.awt.Graphics2D;
//import java.awt.event.KeyEvent;
//
//import levelBuilder.LevelBuilder;
//
//import com.golden.gamedev.GameObject;
//import com.golden.gamedev.object.Background;
//import com.golden.gamedev.object.PlayField;
//import com.golden.gamedev.object.SpriteGroup;
//
//import core.EventManager;
//import core.VoogaGame;
//
//public class MarioGame extends VoogaGame {
//	public static EventManager myEventManager;
//
//	public static void main(String[] args) {
//		launchGame(new MarioGame(), new Dimension(750, 400), false);
//	}
//
//	public PlayField playfield;
//
//	public Background background;
//	public SpriteGroup CHARACTER, PROJECTILE, POWER_UP, PLATFORM, SPAWNPOINT,
//	COINS, BAD_GUYS, INTERACTIVE_SPRITES, EXIT;
//
//	@Override
//	public void initResources() {
//		playfield = new LevelBuilder(this).createLevel(PlatformGame.LEVEL_FILES
//				.get(PlatformGame.currentLevel));
//
//	}
//
//	@Override
//	public void update(long arg0) {
//		playfield.update(arg0);
//		EventManager.getEventManager().update(arg0);
//
//		if (keyDown(KeyEvent.VK_LEFT)) {
//			EventManager.getEventManager().sendEvent("left");
//		}
//		if (keyDown(KeyEvent.VK_RIGHT)) {
//			EventManager.getEventManager().sendEvent("right");
//		}
//		if (keyDown(KeyEvent.VK_UP)) {
//			EventManager.getEventManager().sendEvent("up");
//		}
//		if (keyDown(KeyEvent.VK_DOWN)) {
//			EventManager.getEventManager().sendEvent("down");
//		}
//
//	}
//
//	@Override
//	public void render(Graphics2D arg0) {
//		playfield.render(arg0);
//
//	}
//
//}

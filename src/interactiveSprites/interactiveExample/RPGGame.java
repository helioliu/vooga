package interactiveSprites.interactiveExample;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Random;
import com.golden.gamedev.Game;
import com.golden.gamedev.GameLoader;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.CollisionManager;
import com.golden.gamedev.object.GameFont;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.background.ColorBackground;
import com.golden.gamedev.object.background.ImageBackground;
import com.golden.gamedev.object.collision.AdvanceCollisionGroup;
import com.golden.gamedev.object.collision.BasicCollisionGroup;

public class RPGGame extends Game {

	public static final int PLAYING = 0, TALKING = 1, BATTLE = 2, TITLE = 4, HERO = 0, GIRL = 1;
	int gameState = TITLE;
	int playerTurn = HERO;
	Sprite hero, heroSide, girlSide, s1, s2, s3, s4;
	Spring_IS spring;
	Background background, background2, background3;
	RPGDialog dialog;
	GameFont font;
	BufferedImage box, titleBox;
	int y = 0;
	boolean touching = false;
	boolean moving = false;
	boolean springing;
	CollisionManager collisionType, collisionType2;
	SpriteGroup GIRL_GROUP, BIGCAT_GROUP, INTERACTIVE_SPRITE_GROUP;
	int heroEnergy = 100;
	int girlEnergy = 100;
	String bM = "";
	String bM2 = "";
	Random generator = new Random();
	int phoneNumbers = 0;
	InteractiveSprite movingTarget;
	String[] girlMoves = { "'BITCHY RESPONSE'", "'LOOK AWAY'", "'COLD GAZE'",
			"'EYE ROLL'" };

	

	public void initResources() {

		GIRL_GROUP = new SpriteGroup("Girl Group");
		BIGCAT_GROUP = new SpriteGroup("Big Cat Group");
		INTERACTIVE_SPRITE_GROUP = new SpriteGroup("Interactive Sprite Group");

		background = new ImageBackground(getImage("CeladonCity.png"), 640, 480);
		background2 = new ImageBackground(getImage("BattleField.png"), 640, 480);
		background3 = new ColorBackground(Color.WHITE, 640, 480);

		hero = new Sprite(getImage("BigCat1.png"), 100, 200);
		hero.setLocation(56, 232);

		heroSide = new Sprite(getImage("Chara1Side.png"), 200, 400);
		heroSide.setLocation(100, 320);

		girlSide = new Sprite(getImage("Chara3Side.png"), 200, 400);
		girlSide.setLocation(470, 320);

		spring = new Spring_IS(getImage("MarioSpring.png"), 100, 200, this);
		spring.setLocation(175, 232);

		s1 = new Sprite(getImage("Girl1.png"), 100, 200);
		s1.setLocation(216.7, 109.5);
		s2 = new Sprite(getImage("Girl1.png"), 100, 200);
		s2.setLocation(448.7, 109.5);
		s3 = new Sprite(getImage("Girl1.png"), 100, 200);
		s3.setLocation(335.5, 306.6);
		s4 = new Sprite(getImage("Girl1.png"), 100, 200);
		s4.setLocation(506.5, 306.6);

		GIRL_GROUP.add(s1);
		GIRL_GROUP.add(s2);
		GIRL_GROUP.add(s3);
		GIRL_GROUP.add(s4);
		BIGCAT_GROUP.add(hero);
		INTERACTIVE_SPRITE_GROUP.add(spring);

		collisionType = new BigCatToGirlCollision();
		collisionType.setCollisionGroup(GIRL_GROUP, BIGCAT_GROUP);
		collisionType2 = new BigCatToInteractiveSpriteCollision();
		collisionType2
				.setCollisionGroup(BIGCAT_GROUP, INTERACTIVE_SPRITE_GROUP);

		font = fontManager.getFont(getImage("BitmapFont.png"));
		box = getImage("DialogBox.png", false);
		titleBox = getImage("TitleScreen.png", false);
		dialog = new RPGDialog(fontManager.getFont(getImage("BitmapFont.png")),
				getImage("DialogBox.png", false));

	}

	public void update(long elapsedTime) {

		background.update(elapsedTime);
		GIRL_GROUP.update(elapsedTime);
		BIGCAT_GROUP.update(elapsedTime);
		INTERACTIVE_SPRITE_GROUP.update(elapsedTime);

		collisionType.checkCollision();
		collisionType2.checkCollision();

				if (keyDown(KeyEvent.VK_DOWN)) {
			if ((hero.getX() <= 237 && hero.getX() >= 52 && hero.getY() <= 251)
					|| (hero.getX() <= 525 && hero.getX() >= 230 && hero.getY() <= 338)
					|| (hero.getX() <= 595 && hero.getX() >= 182 && hero.getY() <= 158)) {
				hero.move(0, .1 * elapsedTime);
			}
			gameState = PLAYING;
			touching = false;
		}
		if (keyDown(KeyEvent.VK_UP)) {
			if ((hero.getX() <= 237 && hero.getX() >= 52 && hero.getY() >= 153)
					|| (hero.getX() <= 525 && hero.getX() >= 230 && hero.getY() >= 257)
					|| (hero.getX() <= 595 && hero.getX() >= 182 && hero.getY() >= 115)) {
				hero.move(0, -.1 * elapsedTime);
			}
			gameState = PLAYING;
			touching = false;
		}
		if (keyDown(KeyEvent.VK_LEFT)) {
			if ((hero.getX() >= 52 && hero.getY() >= 153 && hero.getY() <= 251)
					|| (hero.getX() >= 230 && hero.getY() >= 257 && hero.getY() <= 338)
					|| (hero.getX() >= 182 && hero.getY() >= 115 && hero.getY() <= 158)) {
				hero.move(-.1 * elapsedTime, 0);
			}
			gameState = PLAYING;
			touching = false;
		}
		if (keyDown(KeyEvent.VK_RIGHT)) {
			if ((hero.getX() <= 237 && hero.getY() >= 153 && hero.getY() <= 251)
					|| (hero.getX() <= 525 && hero.getY() >= 257 && hero.getY() <= 338)
					|| (hero.getX() <= 595 && hero.getY() >= 115 && hero.getY() <= 158)) {
				hero.move(.1 * elapsedTime, 0);
			}
			gameState = PLAYING;
			touching = false;
		}
		if (keyPressed(KeyEvent.VK_Z)) {

			dialog.setDialog();
			System.out.println("X: " + hero.getX());
			System.out.println("Y: " + hero.getY());
			if (touching)
				gameState = TALKING;
		}
		if (keyPressed(KeyEvent.VK_X)) {
			if (touching) {
				gameState = BATTLE;
				heroEnergy = girlEnergy = 100;
				playerTurn = HERO;

			}
			bM = "A NEW CONVERSATION BEGINS!";
			bM2 = "           PICK A MOVE!";
		}

		if (keyPressed(KeyEvent.VK_A) && playerTurn == HERO) {
			if (gameState == BATTLE) {

				if (heroEnergy >= 1) {
					int temp = girlEnergy;
					girlEnergy = girlEnergy - 10 - generator.nextInt(25);
					bM = "BIG CAT USES 'COMPLIMENT'! GOOD FOR "
							+ Integer.toString(temp - girlEnergy) + " DAMAGE!";
					bM2 = "PRESS [SPACE] TO CONTINUE";
					playerTurn = GIRL;
				} else {

					bM = "GIRL DEFEATS AND EMBARRASSES BIG CAT!";
					bM2 = "USE ARROW KEYS TO EXIT";
				}

			}

		}

		if (keyPressed(KeyEvent.VK_S)) {
			if (gameState == BATTLE) {

				if (heroEnergy >= 1) {
					int temp = girlEnergy;
					girlEnergy = girlEnergy - 10 - generator.nextInt(25);
					bM = "BIG CAT USES 'FUNNY STORY'! GOOD FOR "
							+ Integer.toString(temp - girlEnergy) + " DAMAGE!";
					bM2 = "PRESS [SPACE] TO CONTINUE";
					playerTurn = GIRL;
				} else {
					bM = "GIRL DEFEATS AND EMBARRASSES BIG CAT!";
					bM2 = "USE ARROW KEYS TO EXIT";
				}

			}

		}

		if (keyPressed(KeyEvent.VK_D) && playerTurn == HERO) {
			if (gameState == BATTLE) {

				if (heroEnergy >= 1) {
					int temp = girlEnergy;
					girlEnergy = girlEnergy - 10 - generator.nextInt(25);
					bM = "BIG CAT USES 'FINANCE LINGO'! GOOD FOR "
							+ Integer.toString(temp - girlEnergy) + " DAMAGE!";
					bM2 = "PRESS [SPACE] TO CONTINUE";
					playerTurn = GIRL;
				} else {
					bM = "GIRL DEFEATS AND EMBARRASSES BIG CAT!";
					bM2 = "USE ARROW KEYS TO EXIT";
				}

			}

		}

		if (keyPressed(KeyEvent.VK_F) && playerTurn == HERO) {
			if (gameState == BATTLE && phoneNumbers > 2) {

				if (heroEnergy >= 1) {
					int temp = girlEnergy;
					girlEnergy = girlEnergy - 20 - generator.nextInt(25);
					bM = "BIG CAT USES 'TIPPY TAP'! INCREDIBLE! "
							+ Integer.toString(temp - girlEnergy) + " DAMAGE!";
					bM2 = "PRESS [SPACE] TO CONTINUE";
					playerTurn = GIRL;
				} else {
					bM = "GIRL DEFEATS AND EMBARRASSES BIG CAT!";
					bM2 = "USE ARROW KEYS TO EXIT";
				}

			}

		}

		if (keyPressed(KeyEvent.VK_SPACE) && playerTurn == GIRL) {
			if (gameState == BATTLE) {

				if (girlEnergy >= 1) {
					int temp = heroEnergy;
					heroEnergy = heroEnergy - 10 - generator.nextInt(35);
					bM = "GIRL USES "
							+ girlMoves[generator.nextInt(girlMoves.length)]
							+ "! GOOD FOR "
							+ Integer.toString(temp - heroEnergy) + " DAMAGE!";
					bM2 = "        PICK A MOVE!";
					playerTurn = HERO;
				} else {
					bM = "YOU'VE DEFEATED GIRL AND GOT A PHONE NUMBER!";
					bM2 = "USE ARROWS KEYS TO EXIT";
					phoneNumbers += 1;
				}

			}

		}

		if (!keyDown(KeyEvent.VK_Q)) {
			moving = false;
		}

		if (keyDown(KeyEvent.VK_Q)) {

			if (touching)
				moving = true;

			if (moving) {
				movingTarget.userMove();
			}

		}

	}

	public void render(Graphics2D g) {

		GIRL_GROUP.render(g);
		BIGCAT_GROUP.render(g);
		INTERACTIVE_SPRITE_GROUP.render(g);
		background.render(g);
		hero.render(g);
		s1.render(g);
		s2.render(g);
		s3.render(g);
		s4.render(g);
		spring.render(g);
		
		font.drawString(g, "PHONE NUMBERS: " + Integer.toString(phoneNumbers),
				20, 15);

		if (gameState == TITLE) {
			background3.render(g);
			g.drawImage(titleBox, 0, 0, null);

		}

		if (gameState == PLAYING)
			playMusic("AzaleaTown.mid");

		if (gameState == TALKING) {
			if (hero.getY() <= 230)
				y = 320;
			else
				y = 0;
			g.drawImage(box, 0, y, null);
			font.drawString(g, dialog.myText, 25, y + 60);
		}

		if (gameState == BATTLE) {

			playMusic("TeamRocket.mid");
			background2.render(g);
			heroSide.render(g);
			girlSide.render(g);
			g.drawImage(box, 0, 0, null);
			font.drawString(g,
					"BIG CAT ENERGY: " + Integer.toString(heroEnergy), 25, 175);
			font.drawString(g, "GIRL ENERGY: " + Integer.toString(girlEnergy),
					400, 175);
			font.drawString(g, "A: COMPLIMENT", 25, 220);
			font.drawString(g, "S: FUNNY STORY", 25, 245);
			font.drawString(g, "D: FINANCE LINGO", 25, 270);
			if (phoneNumbers > 2)
				font.drawString(g, "F: TIPPY TAP", 25, 295);
			font.drawString(g, bM, 25, 70);
			font.drawString(g, bM2, 150, 125);

		}

	}

	class BigCatToGirlCollision extends BasicCollisionGroup {

		@Override
		public void collided(Sprite s1, Sprite s2) {
			touching = true;
			// movingTarget = s1;
		}

	}

	class BigCatToInteractiveSpriteCollision extends AdvanceCollisionGroup {

		@Override
		public void collided(Sprite s1, Sprite s2) {

			touching = true;

			((InteractiveSprite) s2).primaryAction(this);
			movingTarget = (InteractiveSprite) s2;

		}
	}

	public static void main(String[] args) {
		GameLoader game = new GameLoader();
		game.setup(new RPGGame(), new Dimension(640, 480), false);
		game.start();
	}

}

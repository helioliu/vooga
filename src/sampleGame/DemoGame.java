package sampleGame;
import hudDisplay.FollowGraphicItem;
import hudDisplay.FollowTextItem;
import hudDisplay.GraphicItem;
import hudDisplay.HeadsUpDisplay;
import hudDisplay.NumberStat;
import hudDisplay.TextItem;
import hudDisplay.TimerItem;
import input.InputManager;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Map;
import sprites.BryanSprite;
import sprites.GeneralSprite;
import States.State;

import com.golden.gamedev.Game;
import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameObject;
import com.golden.gamedev.object.CollisionManager;
import com.golden.gamedev.object.GameFont;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.background.ColorBackground;



public class DemoGame extends Game{
	GeneralSprite s1;
	NumberStat timeStat;
	NumberStat generalTime;
	Map<String, State> stateMap;
	PlayField playfield;
	CollisionManager collisionTypeWall;
	HeadsUpDisplay HUD;
	GameFont scoreFont;
	
	
	@Override
	public void initResources() {
		playfield = new PlayField();
		playfield.setBackground(new ColorBackground(Color.LIGHT_GRAY, 1200, 900));
		HUD = new HeadsUpDisplay(getImage("images/EmptyHUD2.png"), 0, 0);

		s1 = new GeneralSprite(getImage("images/mario1.png"),300,200);
		s1.createStat("health", new NumberStat(300));
		s1.createStat("mana", new NumberStat(300));
		s1.createStat("score", new NumberStat(0));

		timeStat = new NumberStat(0);
		generalTime = new NumberStat(0);

		SpriteGroup character = new SpriteGroup("character");
		character.add(s1);

		GraphicItem healthbar = new GraphicItem(getImage("images/healthbar.png", false), 75, 3, s1.getStat("health"));
		healthbar.setToFlash(true, 100);
		HUD.addItem(healthbar);
		FollowGraphicItem manabar = new FollowGraphicItem(getImage("images/healthBar2.png", false), -50, -20, s1.getStat("mana"),s1);
		HUD.addItem(manabar);
		scoreFont = fontManager.getFont(getImages("images/Score_Font.png", 8,12));
		TextItem score = new TextItem(scoreFont, 500, 10, s1.getStat("score"));
		HUD.addItem(score);
		generalTime.incrementWithTimer(100, 100);
		TimerItem generalTimer = new TimerItem(scoreFont, 400, 10, generalTime);
		HUD.addItem(generalTimer);
		timeStat.incrementWithTimer(500, 1);
		TextItem timerScore = new TextItem(scoreFont, 25, 10, timeStat);
		HUD.addItem(timerScore);
		FollowTextItem Followscore = new FollowTextItem(scoreFont, -50, -40, timeStat, s1);
		HUD.addItem(Followscore);
		
	}

	@Override
	public void render(Graphics2D arg0) {
		playfield.render(arg0);
		collisionTypeWall.checkCollision();
		HUD.render(arg0);		
	}

	protected void initEngine() {
		super.initEngine();
		this.bsInput = new InputManager(this.bsGraphics.getComponent());
	}
	
	@Override
	public void update(long arg0) {
		// TODO Auto-generated method stub
		
	}

}

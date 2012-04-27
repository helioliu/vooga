package interactiveSprites.interactiveTest;

import game.Platformer;
import input.InputManager;
import interactiveSprites.InteractiveSpriteCollision;
import interactiveSprites.KoopaShell_IS;
import interactiveSprites.Spring_IS;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;


import sprites.Character;
import sprites.Chris_TestSprite;

import sprites.WalkingBadGuy;
import States.State;
import com.golden.gamedev.Game;
import com.golden.gamedev.object.CollisionManager;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.Timer;
import com.golden.gamedev.object.background.ColorBackground;
import com.golden.gamedev.object.collision.AdvanceCollisionGroup;
import com.golden.gamedev.object.collision.BasicCollisionGroup;
import core.EventManager;
import cutscenes.Cutscene;

public class Sam_TestGame extends Game{
    Sprite hero;
    Sprite spring;
    Map<String, State> stateMap;
    PlayField playfield;
    CollisionManager collisionTypeIS;
    CollisionManager collisionTypeBlocker;
    Platformer game;

    public void initResources() {
    	
        playfield = new PlayField();
        playfield.setBackground(new ColorBackground(Color.LIGHT_GRAY, 1200, 900));

        hero = new Chris_TestSprite();
        hero.setImage(getImage("images/mario1.png"));
        hero.setLocation(100, 200);
        SpriteGroup character = new SpriteGroup("character");
        character.add(hero);
        
        spring = new Spring_IS(game);
        spring.setImage(getImage("images/MarioSpring.png"));
        spring.setLocation(350, 260);
        SpriteGroup iSprites = new SpriteGroup("iSprites");
        iSprites.add(spring);

        collisionTypeIS = new InteractiveSpriteCollision();
        collisionTypeIS.setCollisionGroup(character, iSprites);
        
        playfield.addGroup(character);
        playfield.addGroup(iSprites);

    }

    public void render(Graphics2D arg0) {
        playfield.render(arg0);
        collisionTypeIS.checkCollision();
    }

    public void update(long elapsedTime) {

        EventManager.getEventManager().update(elapsedTime);
        playfield.update(elapsedTime);
    }

    protected void initEngine() {
		super.initEngine();
		this.bsInput = new InputManager(this.bsGraphics.getComponent());
	}
    


}




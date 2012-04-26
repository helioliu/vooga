package core.test;

import input.InputManager;
import interactiveSprites.InteractiveSpriteCollision;
import interactiveSprites.Spring_IS;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import sprites.Chris_TestSprite;
import sprites.GeneralSprite;
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
import core.conditions.DelayedCondition;
import core.conditions.FloorCollision;
import cutscenes.BadFileFormatException;
import cutscenes.Cutscene;
import cutscenes.CutsceneAutomation;
import cutscenes.CutsceneTrigger;
import cutscenes.EventAutomation;


public class Chris_TestGame extends Game{
    Sprite s1;
    Map<String, State> stateMap;
    PlayField playfield;
    CollisionManager collisionTypeWall;
    CollisionManager collisionTypeBlocker;
    CollisionManager collisionTypeSwitch;
    CollisionManager collisionTypeCut;
    CollisionManager collisionTypeIS;
    Cutscene myCutscene;
    String Filepath;

    public Chris_TestGame(String filepath){
    	Filepath=filepath;
    }

    public void initResources() {
        //		stateMap = new HashMap<String, State>();
        playfield = new PlayField();
        playfield.setBackground(new ColorBackground(Color.LIGHT_GRAY, 1200, 900));

        s1 = new Chris_TestSprite();
        //BufferedImage[] images = new BufferedImage[1];
        //	images[0] = ;
        s1.setImage(getImage("images/mario1.png"));
        s1.setLocation(300, 200);
        SpriteGroup character = new SpriteGroup("character");
        character.add(s1);

        //added by Ben
        Sprite enemy1 = new WalkingBadGuy ();
        enemy1.setImage(getImage("images/thebadguy.png"));
        enemy1.setLocation(350,200);
        enemy1.setMovement(0.025, 270);


        Sprite blocker1 = new Sprite(getImage("images/block.png"));
        Sprite blocker2 = new Sprite(getImage("images/block.png"));
        Sprite blocker3 = new Sprite(getImage("images/block.png"));
        Sprite blocker4 = new Sprite(getImage("images/block.png"));

        blocker1.setLocation(200, 200);
        blocker2.setLocation(500,200);
        blocker3.setLocation(490,100);
        blocker4.setLocation(210,100);
        //


        Sprite wall1 = new Sprite(getImage("images/bricks1.png"));
        wall1.setLocation(275,450);
        Sprite wall2 = new Sprite(getImage("images/bricks1.png"));
        wall2.setLocation(225,450);
        Sprite wall3 = new Sprite(getImage("images/bricks1.png"));
        wall3.setLocation(250,450);
        Sprite wall4 = new Sprite(getImage("images/bricks1.png"));
        wall4.setLocation(300,450);
        Sprite wall5 = new Sprite(getImage("images/bricks1.png"));
        wall5.setLocation(325,450);
        Sprite wall6 = new Sprite(getImage("images/bricks1.png"));
        wall6.setLocation(350,450);
        Sprite wall7 = new Sprite(getImage("images/bricks1.png"));
        wall7.setLocation(375,450);
        Sprite wall8 = new Sprite(getImage("images/bricks1.png"));
        wall8.setLocation(400,450);
        Sprite wall9 = new Sprite(getImage("images/bricks1.png"));
        wall9.setLocation(50,450);
        Sprite wall10 = new Sprite(getImage("images/bricks1.png"));
        wall10.setLocation(75,450);
        Sprite wall11 = new Sprite(getImage("images/bricks1.png"));
        wall11.setLocation(100,450);
        Sprite wall12 = new Sprite(getImage("images/bricks1.png"));
        wall12.setLocation(125,450);
        Sprite wall13 = new Sprite(getImage("images/bricks1.png"));
        wall13.setLocation(425,450);
        Sprite wall14 = new Sprite(getImage("images/bricks1.png"));
        wall14.setLocation(500,300);
        Sprite wall15 = new Sprite(getImage("images/bricks1.png"));
        wall15.setLocation(525,300);
        Sprite wall16 = new Sprite(getImage("images/bricks1.png"));
        wall16.setLocation(550,300);

        //added by Ben
        SpriteGroup enemies = new SpriteGroup("enemies");
        SpriteGroup blockers = new SpriteGroup("blockers");
        enemies.add(enemy1);
        blockers.add(blocker1);
        blockers.add(blocker2);
        blockers.add(blocker3);
        //blockers.add(blocker4);
        //
        
        SpriteGroup cutscene = new SpriteGroup("cut");
        cutscene.add(blocker4);

        SpriteGroup walls = new SpriteGroup("walls");
        walls.add(wall1);
        walls.add(wall2);
        walls.add(wall3);
        walls.add(wall4);
        walls.add(wall5);
        walls.add(wall6);
        walls.add(wall7);
        walls.add(wall8);
        walls.add(wall9);
        walls.add(wall10);
        walls.add(wall11);
        walls.add(wall12);
        walls.add(wall13);
        walls.add(wall14);
        walls.add(wall15);
        walls.add(wall16);
        
//        GeneralSprite spring = new Spring_IS();
//        spring.setImage(getImage("images/MarioSpring.png"));
//        spring.setLocation(350, 260);
//        SpriteGroup iSprites = new SpriteGroup("iSprites");
//        iSprites.add(spring);
//
//        collisionTypeIS = new InteractiveSpriteCollision();
//        collisionTypeIS.setCollisionGroup(character, iSprites);
        
        //playfield.addGroup(iSprites);

        //added by Ben
//        collisionTypeBlocker = new CantGoFurtherCollision();
        playfield.addCollisionGroup(enemies, blockers, new CantGoFurtherCollision());
        playfield.addCollisionGroup(character, walls, new WallCollision());
        playfield.addCollisionGroup(character, blockers, new SwitchCollision());
        playfield.addCollisionGroup(character, cutscene, new CantGoFurtherCollision());
//        collisionTypeBlocker.setCollisionGroup(enemies, blockers);
        //
        collisionTypeWall = new WallCollision();
        collisionTypeWall.setCollisionGroup(character, walls);
        
        collisionTypeSwitch = new SwitchCollision();
        collisionTypeSwitch.setCollisionGroup(character, blockers);
        
        collisionTypeCut = new CutsceneCollision();
        collisionTypeCut.setCollisionGroup(character, cutscene);

        playfield.addGroup(character);
        playfield.addGroup(walls);
        playfield.addGroup(cutscene);

        //added by Ben
        playfield.addGroup(enemies);
        playfield.addGroup(blockers);
        //
        EventAutomation automation = null;
        try {
			automation = new CutsceneAutomation("src/cutscenes/test/testCutsceneScript.script");
		} catch (FileNotFoundException  e) {
			e.printStackTrace();
		}
        catch(BadFileFormatException e){
        	e.printStackTrace();
        }
        
        
        myCutscene = new Cutscene(automation, "start-cutscene","end-cutscene");





    }

    public void render(Graphics2D arg0) {
        
        
        playfield.render(arg0);
    }

    public void update(long elapsedTime) {
        //Cutscene Code
        //		if(cutTimer.action(elapsedTime)) {
        //			trigger.triggerCutscene();
        //			cutTimer.setActive(false);
        //		}
        //		cutscene.update(elapsedTime);

        EventManager.getEventManager().update(elapsedTime);
        playfield.update(elapsedTime);
        //		HUD.update(elapsedTime);
        myCutscene.update(elapsedTime);
        
        collisionTypeWall.checkCollision();
        //added by Ben
//        collisionTypeBlocker.checkCollision();
        //	HUD.render(arg0);
        collisionTypeSwitch.checkCollision();
        collisionTypeCut.checkCollision();
//        collisionTypeIS.checkCollision();

    }

    protected void initEngine() {
		super.initEngine();
		this.bsInput = new InputManager(this.bsGraphics.getComponent());
	}
    
    class WallCollision extends BasicCollisionGroup {

        public WallCollision() {
            pixelPerfectCollision = true;
        }

        public void collided(Sprite s1, Sprite s2) {
        	
//        	EventManager.getEventManager().sendEvent("landed");
            EventManager.getEventManager().sendEvent("floor collide");
            //System.out.println("floor collide");
            //EventManager.getEventManager().sendEvent("switchstates");


        }

    }
    class SwitchCollision extends BasicCollisionGroup {

        public SwitchCollision() {
            pixelPerfectCollision = true;
        }
        
        public void collided(Sprite s1, Sprite s2) {
        	System.out.println("switchstates");
            EventManager.getEventManager().sendEvent("switchstates");
            s2.setActive(false);


        }

    }
    class CutsceneCollision extends BasicCollisionGroup {

        public CutsceneCollision() {
            pixelPerfectCollision = true;
        }

        public void collided(Sprite s1, Sprite s2) {
            EventManager.getEventManager().sendEvent("pwrup");
            s2.setActive(false);


        }

    }

    class CantGoFurtherCollision extends AdvanceCollisionGroup{
        public CantGoFurtherCollision(){
            pixelPerfectCollision = true;
        }

        @Override
        public void collided(Sprite s1, Sprite s2) {
            if(getCollisionSide()==1){
                EventManager.getEventManager().sendEvent("walk right");
            }
            else if( getCollisionSide()==2){
                EventManager.getEventManager().sendEvent("walk up");
            }
            else if ( getCollisionSide()== 4){
                EventManager.getEventManager().sendEvent("walk left");
            }
            else EventManager.getEventManager().sendEvent("walk down");



        }
    }



}

package core.test;

import input.InputManager;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

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
import cutscenes.CutsceneTrigger;


public class Chris_TestGame extends Game{
    Sprite s1;
    Map<String, State> stateMap;
    PlayField playfield;
    CollisionManager collisionTypeWall;
    CollisionManager collisionTypeBlocker;




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


        Sprite wall1 = new Sprite(getImage("images/block.png"));
        wall1.setLocation(350,400);
        Sprite wall2 = new Sprite(getImage("images/block.png"));
        wall2.setLocation(300,400);
        Sprite wall3 = new Sprite(getImage("images/block.png"));
        wall3.setLocation(200,400);
        Sprite wall4 = new Sprite(getImage("images/block.png"));
        wall4.setLocation(250,400);

        //added by Ben
        SpriteGroup enemies = new SpriteGroup("enemies");
        SpriteGroup blockers = new SpriteGroup("blockers");
        enemies.add(enemy1);
        blockers.add(blocker1);
        blockers.add(blocker2);
        blockers.add(blocker3);
        blockers.add(blocker4);
        //

        SpriteGroup walls = new SpriteGroup("walls");
        walls.add(wall1);
        walls.add(wall2);
        walls.add(wall3);
        walls.add(wall4);

        //added by Ben
        collisionTypeBlocker = new CantGoFurtherCollision();
        collisionTypeBlocker.setCollisionGroup(enemies, blockers);
        //
        collisionTypeWall = new WallCollision();
        collisionTypeWall.setCollisionGroup(character, walls);

        playfield.addGroup(character);
        playfield.addGroup(walls);

        //added by Ben
        playfield.addGroup(enemies);
        playfield.addGroup(blockers);
        //





    }

    public void render(Graphics2D arg0) {
        playfield.render(arg0);
        collisionTypeWall.checkCollision();
        //added by Ben
        collisionTypeBlocker.checkCollision();
        //	HUD.render(arg0);
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
            EventManager.getEventManager().sendEvent("floor collide");
            EventManager.getEventManager().sendEvent("switchstates");


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

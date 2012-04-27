package core.test;

import input.InputManager;
import interactiveSprites.InteractiveSpriteCollision;
import interactiveSprites.Spring_IS;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.util.Map;

import levelBuilder.LevelBuilder;

import sprites.Chris_TestSprite;
import sprites.GeneralSprite;
import sprites.WalkingBadGuy;
import States.State;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.CollisionManager;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.background.ColorBackground;
import com.golden.gamedev.object.collision.AdvanceCollisionGroup;
import com.golden.gamedev.object.collision.BasicCollisionGroup;

import core.EventManager;
import core.conditions.FloorCollision;
import cutscenes.BadFileFormatException;
import cutscenes.Cutscene;
import cutscenes.CutsceneAutomation;
import cutscenes.EventAutomation;


public class Dick_TestGame extends Game{
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

    public Dick_TestGame(String filepath){
    	Filepath=filepath;
    }

    public void initResources() {
        //		stateMap = new HashMap<String, State>();
        playfield = new PlayField();
        
        
        SpriteGroup character = new SpriteGroup("character");
        SpriteGroup walls = new SpriteGroup("walls");
        SpriteGroup blockers = new SpriteGroup("blockers");
        
        playfield.addGroup(character);
        playfield.addGroup(walls);
        playfield.addGroup(blockers);
        
        playfield = new LevelBuilder(playfield,Filepath).createLevel();
                   



//        collisionTypeBlocker = new CantGoFurtherCollision();
        playfield.addCollisionGroup(character, walls, new WallCollision());
        playfield.addCollisionGroup(character, blockers, new SwitchCollision());
//        collisionTypeBlocker.setCollisionGroup(enemies, blockers);
        //
        collisionTypeWall = new FloorCollision();
        collisionTypeWall.setCollisionGroup(character, walls);
        
        collisionTypeSwitch = new SwitchCollision();
        collisionTypeSwitch.setCollisionGroup(character, blockers);
        


        //
  


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

        collisionTypeWall.checkCollision();
        //added by Ben
//        collisionTypeBlocker.checkCollision();
        //	HUD.render(arg0);
        collisionTypeSwitch.checkCollision();
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

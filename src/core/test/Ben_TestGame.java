package core.test;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import sprites.Bens_TestSprite;
import sprites.BulletShooter;
import sprites.Chris_TestSprite;
import sprites.Enemy;
import sprites.HomingEnemy;
import sprites.HomingProjectile;
import sprites.Projectile;
import sprites.StateSprite;
import sprites.WalkingBadGuy;
import States.State;
import States.StationaryState;

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



public class Ben_TestGame extends Game{


    Map<String, State> stateMap;
    PlayField playfield;
    CollisionManager collisionTypeWall;
    CollisionManager collisionTypeBlocker;
    CollisionManager collisionTypeKill;
    CollisionManager collisionTypeKill2;
    SpriteGroup HERO, BLOCKERS, ENEMIES, ENEMY_MISSLES, BULLET_MISSLES, TURRETS;
    StateSprite s1, enemy1, enemy2, enemy3, enemy4;
    Timer EnemyFireRate = new Timer(2000);
    Timer TurretFireRate = new Timer(3000);




    public void initResources() {

        playfield = new PlayField();
        playfield.setBackground(new ColorBackground(Color.LIGHT_GRAY, 1200, 900));


        s1 = new Bens_TestSprite();
        s1.setImage(getImage("images/thegoodguy.png"));
        s1.setLocation(300, 200);
        HERO = new SpriteGroup("character");
        HERO.add(s1);

        //added by Ben
        enemy1 = new WalkingBadGuy ();
        enemy1.setImage(getImage("images/thebadguy.png"));
        enemy1.setLocation(350,200);
        enemy1.setMovement(0.025, 90);
        enemy2 = new HomingEnemy(s1);
        enemy2.setImage(getImage("images/boo.jpg"));
        enemy2.setLocation(500, 300);
        enemy3 = new HomingEnemy(s1);
        enemy3.setImage(getImage("images/boo.jpg"));
        enemy3.setLocation(100, 300);
        
         enemy4 = new BulletShooter();
        enemy4.setImage(getImage("images/bulletshooter.png"));
        enemy4.setLocation(200, 300);


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
        ENEMIES = new SpriteGroup("enemies");
        BLOCKERS = new SpriteGroup("blockers");
        ENEMIES.add(enemy1);
        ENEMIES.add(enemy2);
        ENEMIES.add(enemy3);
        BLOCKERS.add(blocker1);
        BLOCKERS.add(blocker2);
        BLOCKERS.add(blocker3);
        BLOCKERS.add(blocker4);
        //

        ENEMY_MISSLES = new SpriteGroup ("Enemy Missles");
        
        TURRETS = new SpriteGroup("turrets");
        TURRETS.add(enemy4);
        
        BULLET_MISSLES = new SpriteGroup("bullet missles");




        //added by Ben
        collisionTypeBlocker = new CantGoFurtherCollision();
        collisionTypeBlocker.setCollisionGroup(ENEMIES, BLOCKERS);
        collisionTypeKill = new KillCollision();
        collisionTypeKill.setCollisionGroup(BULLET_MISSLES, HERO);
        collisionTypeKill2 = new KillCollision();
        collisionTypeKill2.setCollisionGroup(ENEMY_MISSLES, HERO);
        //





        //added by Ben
        playfield.addGroup(ENEMIES);
        playfield.addGroup(TURRETS);
        playfield.addGroup(BLOCKERS);
        playfield.addGroup(HERO);
        playfield.addGroup(ENEMY_MISSLES);
        playfield.addGroup(BULLET_MISSLES);
        //





    }

    public void render(Graphics2D arg0) {
        playfield.render(arg0);

        //added by Ben
        collisionTypeBlocker.checkCollision();
     //   collisionTypeKill.checkCollision();
     //   collisionTypeKill2.checkCollision();
        //  HUD.render(arg0);
    }

    public void update(long elapsedTime) {

       
        if(enemy2.getDistance(s1)>100){
            EventManager.getEventManager().sendEvent("stationary" + enemy2.hashCode());
        }
        else{
            EventManager.getEventManager().sendEvent("homing" + enemy2.hashCode());
        }
        
        if(enemy3.getDistance(s1)>100){
            EventManager.getEventManager().sendEvent("stationary" + enemy3.hashCode());
        }
        else{
            EventManager.getEventManager().sendEvent("homing" + enemy3.hashCode());
        }
       
        ((WalkingBadGuy)enemy1).Shoot(elapsedTime, EnemyFireRate, ENEMY_MISSLES, getImage("images/fireball.png"), s1 );
        
        ((BulletShooter)enemy4).Shoot(elapsedTime, TurretFireRate, BULLET_MISSLES, getImage("images/bulletbill.png"), s1 );

        EventManager.getEventManager().update(elapsedTime);
        playfield.update(elapsedTime);

        if (keyDown(KeyEvent.VK_LEFT))
        {
            EventManager.getEventManager().sendEvent("Left");
        }
        if (keyDown(KeyEvent.VK_RIGHT))
        {
            EventManager.getEventManager().sendEvent("Right");
        }
        if (keyDown(KeyEvent.VK_UP))
        {
            EventManager.getEventManager().sendEvent("Up"); 
        }
        if (keyDown(KeyEvent.VK_DOWN))
        {
            EventManager.getEventManager().sendEvent("Down");   
        }
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
    
    class KillCollision extends AdvanceCollisionGroup{
       
        public KillCollision(){
            pixelPerfectCollision = true;
        }

        @Override
        public void collided(Sprite bullet, Sprite hero) {
            bullet.setActive(true);
            hero.setActive(false);
            
        }
        
    }

    class CantGoFurtherCollision extends AdvanceCollisionGroup{
        public CantGoFurtherCollision(){
            pixelPerfectCollision = true;
        }

        @Override
        public void collided(Sprite s1, Sprite s2) {
            if(getCollisionSide()==1){
                EventManager.getEventManager().sendEvent("walk right" + s1.hashCode());
            }
            else if( getCollisionSide()==2){
                EventManager.getEventManager().sendEvent("walk up" + s1.hashCode());
            }
            else if ( getCollisionSide()== 4){
                EventManager.getEventManager().sendEvent("walk left" + s1.hashCode());
            }
            else EventManager.getEventManager().sendEvent("walk down" + s1.hashCode());



        }
    }



}

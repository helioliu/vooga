package collisionTest;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Map;

import sprites.BossSprite;

import States.*;
import collisionType.AbstractHitboxHitboxCollision;
import collisions.Hitbox;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.AdvanceSpriteGroup;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.background.ColorBackground;
import com.golden.gamedev.object.collision.AdvanceCollisionGroup;

import core.EventManager;



public class PlatformGame extends Game{

	Map<String, State> stateMap;
	EventManager eventManager;
	PlayField playfield;
	
	public void initResources() {
		stateMap = new HashMap<String, State>();
		eventManager = EventManager.getEventManager();
		playfield = new PlayField();
        playfield.setBackground(new ColorBackground(Color.WHITE, 1200, 900));

        SpriteGroup projectiles = playfield.addGroup(new AdvanceSpriteGroup("Proj"));
		Sprite s1 = new BoxySprite(getImages("test.png", 2, 2), 0, 200);
		Sprite s2 = new BossSprite(getImages("images/bau5.png", 1, 5), 400, 100, getImage("images/pewpewpew.png"), s1, projectiles);
		
		playfield.add(s1);
		playfield.add(s2);

		s1.setHorizontalSpeed(0.8);
		s2.setHorizontalSpeed(0);
		
		
		SpriteGroup CHAR2 		= playfield.addGroup(new AdvanceSpriteGroup("Char2", 300));
		SpriteGroup CHAR1  		= playfield.addGroup(new AdvanceSpriteGroup("Char1", 0));
		CHAR1.add(s1);
		CHAR2.add(s2);
		
		playfield.addCollisionGroup(CHAR1, projectiles, new AdvanceCollisionGroup(){

			@Override
			public void collided(Sprite arg0, Sprite arg1) {
				// TODO Auto-generated method stub
				pixelPerfectCollision = true;
				arg1.setActive(false);
			}
			
		});
		playfield.addCollisionGroup(CHAR1, CHAR2, new AbstractHitboxHitboxCollision(){

			@Override
			protected void spriteCollided(Sprite s1, Sprite s2) {
				// TODO Auto-generated method stub
				
			}

			@Override
			protected void hitboxSpriteCollided(Sprite s1, Hitbox h1, Sprite s2) {
				// TODO Auto-generated method stub
				
			}

			@Override
			protected void spriteHitboxCollided(Sprite s1, Sprite s2, Hitbox h2) {
				System.out.println(h2.getID());
			}

			@Override
			protected void hitboxCollided(Sprite s1, Hitbox h1, Sprite s2,
					Hitbox h2) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
	}
	
	public void render(Graphics2D arg0) {
		playfield.render(arg0);
	}
	
	public void update(long elapsedTime) {
		eventManager.update(elapsedTime);
		playfield.update(elapsedTime);
	}

}

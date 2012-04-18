package Enemies;
import game.PlatformGame;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.EventListener;


import StateMachines.EnemyStateManager;
import States.EnemyState;
import States.StationaryState;

import com.golden.gamedev.object.Sprite;


public class Enemy extends Sprite{
    
    public boolean jumping;
    private PlatformGame game;
    private EnemyState state;
    protected EnemyStateManager manager;
    private EventListener l;


    private Enemy(){
        super();
        
    }
    public Enemy(BufferedImage image) {
        super(image, 0, 0);
        manager = new EnemyStateManager(this);
        state = new StationaryState(this);
        
        
        
        
    }

    
    public Enemy(PlatformGame currentGame, BufferedImage image) {
        super(image);
        game=currentGame;
        manager = new EnemyStateManager(this);
        state = new StationaryState(this);
     
    }

    @Override
    public void update(long elapsedTime) {      
        super.update(elapsedTime);
        
    }
    
    
    


    @Override
    public void render(Graphics2D g, int x, int y) {
        super.render(g, x, y);
    }

}


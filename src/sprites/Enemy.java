package sprites;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;


import stateManagers.StateManager;
import stateTransitions.StateTransition;


import levelEditor.*;
import game.*;

import States.ReverseMotionState;
import States.StationaryState;
import States.WalkingLeftState;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.Timer;


public abstract class Enemy extends StateSprite {
    // indicates whether this enemy has been show to screen or not
    boolean show;
    
    Platformer myGame;
    // attempt to fire every 400 ms
    Timer	fireRate = new Timer(4000);



    public Enemy() {
        super();
        getStateManager().addState(new WalkingLeftState(this));
    }



    public abstract void Shoot( long elapsedTime, Timer fireRate, SpriteGroup Projectile, BufferedImage Image, Sprite s);
    
    @Override
    public void update(long elapsedTime) {
        super.update(elapsedTime);

    }


}

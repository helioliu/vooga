package sprites;
import java.awt.image.BufferedImage;

import game.*;

import States.StationaryState;
import States.WalkingLeftState;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.Timer;


public abstract class Enemy extends StateSprite {
    // indicates whether this enemy has been show to screen or not
    private  boolean enableShoot = false;
    private Timer   fireRate = new Timer(4000);



    public Enemy() {
        super();
 
        getStateManager().addState(new StationaryState(this));
    }
    public Enemy(BufferedImage[] images, double x, double y) {
        super(images,x,y);
    }
    public boolean isEnableShoot() {
        return enableShoot;
    }



    public void setEnableShoot(boolean enableShoot) {
        this.enableShoot = enableShoot;
    }



    public abstract void Shoot( long elapsedTime, Timer fireRate, SpriteGroup Projectile, BufferedImage Image, Sprite s);
    
    @Override
    public void update(long elapsedTime) {
        super.update(elapsedTime);

    }


}
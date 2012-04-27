package sprites;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.Timer;

import stateTransitions.ChangeStateTransition;
import stateTransitions.StateTransition;
import States.RegularMotionState;
import States.StationaryState;
import States.WalkingDownState;
import States.WalkingLeftState;
import States.WalkingRightState;
import States.WalkingUpState;


public class WalkingBadGuy extends StateSprite {


   
    boolean enableShoot;


    public WalkingBadGuy(){
        super();
        enableShoot = false;
        int tag = this.hashCode();
        StateTransition one = new ChangeStateTransition(getStateManager(), "walk right" + tag, new WalkingRightState(this));
        StateTransition two = new ChangeStateTransition(getStateManager(), "walk left" + tag, new WalkingLeftState(this));
        StateTransition three = new ChangeStateTransition(getStateManager(), "walk up" + tag, new WalkingUpState(this));
        StateTransition four = new ChangeStateTransition(getStateManager(), "walk down" + tag, new WalkingDownState(this));
        one.activate();
        two.activate();
        three.activate();
        four.activate();
    }
   

    public void Shoot( long elapsedTime, Timer fireRate, SpriteGroup Projectile, BufferedImage Image, Sprite s){
        Projectile shot;
        if(enableShoot == true){
            shot = new Projectile(Image);
            shot.setLocation( this.getX()+15, this.getY()-5 );
            shot.fireAtTarget(s);

            Projectile.add(shot);
            enableShoot = false;
            fireRate.refresh();
        }
        else { 
            if (fireRate.action( elapsedTime  ))  
                enableShoot = true; 

        }
    }

    public void Shoot2(long elapsedTime, Timer fireRate, SpriteGroup Projectile, BufferedImage Image, Sprite s){
        HomingProjectile shot;
        if(enableShoot == true){
            shot = new HomingProjectile(Image, s);
            shot.setLocation( this.getX()+15, this.getY()-5 );
            shot.fireAtTarget(s, elapsedTime);
            Projectile.add(shot);
            enableShoot = false;
            fireRate.refresh();
        }
        else { 
            if (fireRate.action( elapsedTime  ))  
                enableShoot = true; 

        }
    }

}


package sprites;

import java.awt.image.BufferedImage;


import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.Timer;

import stateTransitions.ChangeStateTransition;
import stateTransitions.StateTransition;
import States.WalkingDownState;
import States.WalkingLeftState;
import States.WalkingRightState;
import States.WalkingUpState;


@SuppressWarnings("serial")
public class WalkingBadGuy extends Enemy {


   



    public WalkingBadGuy(){
        super();
        setEnableShoot(true);
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
   


    public void Shoot(long elapsedTime, Timer fireRate, SpriteGroup Projectile, BufferedImage Image, Sprite s){
        HomingProjectile shot;
        if(isEnableShoot() == true){
            shot = new HomingProjectile(Image, s);
            shot.setLocation( this.getX()+15, this.getY()-5 );
            shot.fireAtTarget(s, elapsedTime);
            Projectile.add(shot);
            setEnableShoot(false);
            fireRate.refresh();
        }
        else { 
            if (fireRate.action( elapsedTime  ))  
               setEnableShoot(true);

        }
    }

}


package SpriteAction;

import com.golden.gamedev.object.Sprite;
import sprites.HomingProjectile;
import sprites.HomingEnemy;

import sprites.GeneralSprite;

public class Homing extends SpriteAction {

    public Homing(GeneralSprite s) {
        super(s);
    }

    @Override
    public void actionPerformed(Object object) {
        Sprite target = ((HomingEnemy)mySprite).getMyTarget();
        double angleToTarget = Math.atan2( (target.getY()-mySprite.getY()),(target.getX()-mySprite.getX()));

        angleToTarget = Math.toDegrees(angleToTarget);

        if(angleToTarget< 0){
            angleToTarget+=360;
        }
        angleToTarget+=90;
        mySprite.setMovement(.04, angleToTarget);

    }

}

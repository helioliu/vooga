package sprites;

import java.awt.image.BufferedImage;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.Timer;

import core.EventManager;

public class HomingProjectile extends Projectile {
    private boolean needsCorrection= true;
    private Timer homingUpdateSpeed = new Timer(400);
    private Sprite target;

    


    public HomingProjectile(BufferedImage image, Sprite target) {
        super(image);
        this.target = target;
    }
    
    public HomingProjectile(BufferedImage image, Sprite target, double x, double y){
        this(image, target);
        setX(x);
        setY(y);
    }

    public Sprite getTarget() {
        return target;
    }
    
    public void fireAtTarget(Sprite target, long elapsedTime){
        goToTarget(target);
    }



    public Timer getHomingUpdateSpeed() {
        return homingUpdateSpeed;
    }


    public boolean isNeedsCorrection() {
        return needsCorrection;
    }


    public void setNeedsCorrection(boolean needsCorrection) {
        this.needsCorrection = needsCorrection;
    }


    public void goToTarget(Sprite target) {
        double distance = Math.sqrt((Math.pow(target.getX()-this.getX(),2)) + (Math.pow(target.getY()-target.getY(), 2)));
        if(distance>100){
            this.setMovement(.09, 270);
        }
        else{
            double angleToTarget = Math.atan2( (target.getY()-this.getY()),(target.getX()-this.getX()));

            angleToTarget = Math.toDegrees(angleToTarget);

            if(angleToTarget< 0){
                angleToTarget+=360;
            }
            angleToTarget+=90;
            this.setMovement(.04, angleToTarget);
        }
    }
    
    @Override
    public void update(long elapsedTime){
        super.update(elapsedTime);
        this.goToTarget(target);
        
    }

}

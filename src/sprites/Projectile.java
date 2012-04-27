package sprites;
import java.awt.image.BufferedImage;
import java.util.Vector;

import game.*;

import com.golden.gamedev.object.Sprite;


public class Projectile extends GeneralSprite {
    // indicates whether this enemy has been show to screen or not
    boolean show = false;
    Platformer game;

    

    public Projectile(BufferedImage image) {
        super(image);
    }



    public void fireAtTarget(Sprite target){
       double angleToTarget = Math.atan2( (target.getY()-this.getY()),(target.getX()-this.getX()));
       
       angleToTarget = Math.toDegrees(angleToTarget);
       
       if(angleToTarget< 0){
           angleToTarget+=360;
       }
       angleToTarget+=90;
       System.out.println(angleToTarget);
        this.setMovement(.04, angleToTarget);
    }
    public void fireAtTarget(double x, double y)
    {
    	
    	double speedx = (x-this.getScreenX())/((this.getScreenX()-x)*(this.getScreenX()-x)+(this.getScreenY()-y)*(this.getScreenY()-y))*50;
		double speedy = (y-this.getScreenY())/((this.getScreenX()-x)*(this.getScreenX()-x)+(this.getScreenY()-y)*(this.getScreenY()-y))*50;
		this.setSpeed(speedx, speedy);
//    	double angleToTarget = Math.atan2( (y-this.getScreenY()),(x-this.getScreenX()));
//        
//        angleToTarget = Math.toDegrees(angleToTarget);
//        
//        if(angleToTarget< 0){
//            angleToTarget+=360;
//        }
//        angleToTarget+=90;
//         this.setMovement(.2, angleToTarget);
		
    }

  



}

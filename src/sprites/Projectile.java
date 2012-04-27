package sprites;
import java.awt.image.BufferedImage;
import java.util.Vector;

import levelEditor.*;
import game.*;

import com.golden.gamedev.object.Sprite;


public class Projectile extends Sprite {
    // indicates whether this enemy has been show to screen or not
    boolean show = false;
    Platformer game;



    public Projectile(BufferedImage image) {
        super(image,0,0);

    }

    public Projectile(Projectile projectile, Platformer currentGame) {
        super(projectile.getImage(),0,0);
        show=projectile.show;
        game=currentGame;
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

  



}

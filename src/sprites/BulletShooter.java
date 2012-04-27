package sprites;

import java.awt.image.BufferedImage;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.Timer;

public class BulletShooter extends Enemy {
    
    
   public BulletShooter(){
       super();
       setEnableShoot(true);
   }
    
    @Override
    public void Shoot(long elapsedTime, Timer fireRate, SpriteGroup Projectile,
            BufferedImage Image, Sprite s) {
        Projectile shot;
        if(isEnableShoot() == true){
            shot = new Projectile(Image);
            shot.setLocation( this.getX()+15, this.getY()-5 );
            shot.fireAtTarget(s);
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

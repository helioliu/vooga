package collisions;

import com.golden.gamedev.object.collision.CollisionGroup;
import com.golden.gamedev.object.collision.CollisionShape;

//associates a shape and behavior
public class Hitbox {
    
   private CollisionShape myShape;//the hitbox's shape
   private CollisionGroup myBehavior;//the behavior
   
   public Hitbox(CollisionShape shape, CollisionGroup group){
       myShape = shape;
       myBehavior = group;
   }
   
   public void setShape(CollisionShape shape){
       myShape = shape;
   }
   public void setBehavior(CollisionGroup behavior){
       myBehavior = behavior;
   }
   
   public CollisionShape getShape(){
       return myShape;
   }
   public CollisionGroup getBehavior(){
       return myBehavior;
   }

}

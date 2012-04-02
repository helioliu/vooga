package collisions;

import com.golden.gamedev.object.CollisionManager;
import com.golden.gamedev.object.collision.CollisionShape;

//associates a shape and behavior
public class Hitbox {
    
   private CollisionShape myShape;//the hitbox's shape
   private CollisionManager myBehavior;//the behavior
   
   public Hitbox(CollisionShape shape, CollisionManager group){
       myShape = shape;
       myBehavior = group;
   }
   
   public void setShape(CollisionShape shape){
       myShape = shape;
   }
   public void setBehavior(CollisionManager behavior){
       myBehavior = behavior;
   }
   
   public CollisionShape getShape(){
       return myShape;
   }
   public CollisionManager getBehavior(){
       return myBehavior;
   }

}

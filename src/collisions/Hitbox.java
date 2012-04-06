package collisions;

//associates a shape and event
public class Hitbox {
    
   private CollisionShape myShape;//the hitbox's shape
   private String myEvent;//the event to broadcast
   
   public Hitbox(CollisionShape shape, String group){
       myShape = shape;
       myEvent = group;
   }
   
   public void setShape(CollisionShape shape){
       myShape = shape;
   }
   public void setEvent(String event){
       myEvent = event;
   }
   
   public CollisionShape getShape(){
       return myShape;
   }
   public String getEvent(){
       return myEvent;
   }

}
